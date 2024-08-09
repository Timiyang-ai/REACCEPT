public static MountableFile forHostPath(final Path path) {
        return new MountableFile(path.toAbsolutePath().toString());
    }