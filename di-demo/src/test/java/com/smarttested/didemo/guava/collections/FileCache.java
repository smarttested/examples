package com.smarttested.didemo.guava.collections;

import com.google.common.base.Optional;
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

    private File file;

    private String fileName;

    private Instant fileDate;

    private Duration expirationPeriod;


    public FileCache(String fileName, Duration expirationPeriod) throws IOException {
        this.expirationPeriod = expirationPeriod;
        this.fileName = fileName;

        Optional<Path> cacheFile = FileSystemUtils.findPath(StandardSystemProperty.JAVA_IO_TMPDIR.value(), "*" + fileName);
        if (cacheFile.isPresent()) {
            file = cacheFile.get().toFile();
            fileDate = Instant.parse(file.getName().substring(0, file.getName().lastIndexOf('_')));
        }
    }


    public ByteSource load() {
        if (!exists()) {
            throw new IllegalStateException("Cache expired or doesn't exists");
        }
        return Files.asByteSource(file);
    }

    public void store(ByteSource source) throws IOException {
        source.copyTo(Files.asByteSink(createCacheFile()));
    }

    public boolean exists() {
        return null != file && file.exists() && Instant.now().isBefore(fileDate.plus(expirationPeriod));
    }

    private File createCacheFile() {
        fileDate = Instant.now();
        this.file = new File(StandardSystemProperty.JAVA_IO_TMPDIR.value(), fileDate + "_" + fileName);
        return file;
    }


}
