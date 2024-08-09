public static MountableFile forHostPath(final Path path, int mode) {
        return new MountableFile(path.toAbsolutePath().toString(), mode);
    }