public static MountableFile forHostPath(@NotNull final String path, Integer mode) {
        return new MountableFile(new File(path).toURI().toString(), mode);
    }