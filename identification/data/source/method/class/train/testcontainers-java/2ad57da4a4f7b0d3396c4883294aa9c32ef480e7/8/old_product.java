public static MountableFile forHostPath(@NotNull final String path, int mode) {
        return new MountableFile(new File(path).toURI().toString(), mode);
    }