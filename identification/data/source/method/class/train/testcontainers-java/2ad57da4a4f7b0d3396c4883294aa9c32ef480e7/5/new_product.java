public static MountableFile forHostPath(final Path path, Integer mode) {
        return new MountableFile(path.toAbsolutePath().toString(), mode);
    }