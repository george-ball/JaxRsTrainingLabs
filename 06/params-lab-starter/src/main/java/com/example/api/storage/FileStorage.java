package com.example.api.storage;

import java.io.*;
import java.nio.file.*;

/**
 * Simple file store backed by a temporary directory.
 * Complete – do not modify.
 *
 * Used in Lab 5 (Binary Content & File Delivery).
 * Call getInstance() to get the singleton; call reset() between tests.
 */
public class FileStorage {

    private static final FileStorage INSTANCE = new FileStorage();
    public  static FileStorage getInstance() { return INSTANCE; }

    private final Path baseDir;

    private FileStorage() {
        try {
            baseDir = Files.createTempDirectory("params-lab-files-");
            // Pre-create a couple of test files
            Files.write(baseDir.resolve("hello.txt"),
                "Hello from the params lab!\nThis is a test file.\n".getBytes());
            Files.write(baseDir.resolve("data.csv"),
                "id,value\n1,alpha\n2,beta\n3,gamma\n".getBytes());
        } catch (IOException e) {
            throw new RuntimeException("FileStorage init failed", e);
        }
    }

    /** The base directory all files are stored in. */
    public Path getBaseDir() { return baseDir; }

    /** Save raw bytes to a named file. Returns the saved Path. */
    public Path saveBytes(String filename, byte[] data) throws IOException {
        Path dest = baseDir.resolve(filename);
        Files.write(dest, data);
        return dest;
    }

    /** Save an InputStream to a named file. Returns the saved Path. */
    public Path saveStream(String filename, InputStream in) throws IOException {
        Path dest = baseDir.resolve(filename);
        Files.copy(in, dest, StandardCopyOption.REPLACE_EXISTING);
        return dest;
    }

    /** Returns true if the named file exists. */
    public boolean exists(String filename) {
        return Files.exists(baseDir.resolve(filename));
    }

    /** Delete a stored file. Returns true if it existed. */
    public boolean delete(String filename) {
        try {
            return Files.deleteIfExists(baseDir.resolve(filename));
        } catch (IOException e) {
            return false;
        }
    }

    /** List all filenames in the store. */
    public java.util.List<String> listFiles() throws IOException {
        try (var s = Files.list(baseDir)) {
            return s.map(p -> p.getFileName().toString())
                    .sorted()
                    .collect(java.util.stream.Collectors.toList());
        }
    }

    /**
     * Delete all uploaded files and recreate the seed files.
     * Call this in test teardown.
     */
    public void reset() {
        try {
            // Delete all files
            try (var s = Files.list(baseDir)) {
                s.forEach(p -> { try { Files.delete(p); } catch (IOException ignored) {} });
            }
            // Recreate seed files
            Files.write(baseDir.resolve("hello.txt"),
                "Hello from the params lab!\nThis is a test file.\n".getBytes());
            Files.write(baseDir.resolve("data.csv"),
                "id,value\n1,alpha\n2,beta\n3,gamma\n".getBytes());
        } catch (IOException e) {
            throw new RuntimeException("FileStorage reset failed", e);
        }
    }
}
