package com.smarttested.didemo.guava;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.StandardSystemProperty;
import com.google.common.io.ByteSource;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;

/**
 * Simple cache of {@link com.google.common.io.ByteSource} in file. Supports expiration
 *
 * @author avarabyeu
 */
public class FileCache {

    private String fileName;

    private Instant fileDate;

    private Duration expirationPeriod;

    private Optional<CachedFile> cachedFile;

    public FileCache(String fileName, Duration expirationPeriod) throws IOException {
        this.expirationPeriod = expirationPeriod;
        this.fileName = fileName;

        Optional<Path> cacheFile = FileSystemUtils.findPath(StandardSystemProperty.JAVA_IO_TMPDIR.value(), "*" + fileName);
        if (cacheFile.isPresent()) {
            CachedFile fileCache = new CachedFile(cacheFile.get().toFile());
            if (fileCache.expired(expirationPeriod)) {
                cacheFile.get().toFile().delete();
                cachedFile = Optional.absent();
            } else {
                this.cachedFile = Optional.of(fileCache);
            }
        } else {
            this.cachedFile = Optional.absent();
        }
    }


    public ByteSource load() {
        if (!exists()) {
            throw new IllegalStateException("Cache expired or doesn't exists");
        }
        return Files.asByteSource(cachedFile.get().getFile());
    }

    public void store(ByteSource source) throws IOException {
        CachedFile cacheFile = createCacheFile();
        source.copyTo(Files.asByteSink(cacheFile.getFile()));
        this.cachedFile = Optional.of(cacheFile);
    }

    public boolean exists() {
        return cachedFile.isPresent();
    }

    private CachedFile createCacheFile() {
        fileDate = Instant.now();

        return new CachedFile(new File(StandardSystemProperty.JAVA_IO_TMPDIR.value(), fileDate.toEpochMilli() + "_" + fileName));
    }

    public static class CachedFile {
        private File file;
        private Instant createdAt;

        public CachedFile(File file) {
            this.file = Preconditions.checkNotNull(file, "Provided file shouldn't be null");
            this.createdAt = Instant.ofEpochMilli(Long.valueOf(file.getName().substring(0, file.getName().lastIndexOf('_'))));
        }

        public File getFile() {
            return file;
        }

        public boolean expired(Duration lifeTime) {
            return !(file.exists() && Instant.now().isBefore(createdAt.plus(lifeTime)));
        }
    }


}
