package com.smarttested.didemo.guava;

import com.google.common.base.Optional;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.FileVisitResult.CONTINUE;
import static java.nio.file.FileVisitResult.TERMINATE;

/**
 * @author avarabyeu
 */
public class FileSystemUtils {

    /**
     * Finds file path recursively
     *
     * @param root            Root directory for file search
     * @param fileNamePattern File name pattern
     * @return
     * @throws java.io.IOException
     */
    public static Optional<Path> findPath(Path root, String fileNamePattern) throws IOException {
        Finder finder = new Finder(fileNamePattern);
        Files.walkFileTree(root, finder);
        return finder.getFound();
    }


    /**
     * Finds file path recursively
     *
     * @param root            Root directory for file search
     * @param fileNamePattern File name pattern
     * @return
     * @throws java.io.IOException
     */
    public static Optional<Path> findPath(String root, String fileNamePattern) throws IOException {
        return findPath(FileSystems.getDefault().getPath(root), fileNamePattern);
    }


    public static class Finder
            extends SimpleFileVisitor<Path> {

        private final PathMatcher matcher;
        private Optional<Path> found = Optional.absent();

        Finder(String pattern) {
            matcher = FileSystems.getDefault()
                    .getPathMatcher("glob:" + pattern);
        }

        // Compares the glob pattern against
        // the file or directory name.
        FileVisitResult find(Path file) {
            Path name = file.getFileName();
            if (name != null && matcher.matches(name)) {
                found = Optional.of(file);
                return TERMINATE;
            }
            return CONTINUE;
        }

        // Invoke the pattern matching
        // method on each file.
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            return find(file);
        }

        // Invoke the pattern matching
        // method on each directory.
        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
            return find(dir);
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) {
            return CONTINUE;
        }

        public Optional<Path> getFound() {
            return found;
        }
    }
}
