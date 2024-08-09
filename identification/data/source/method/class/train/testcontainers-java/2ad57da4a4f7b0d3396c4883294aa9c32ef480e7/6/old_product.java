public static MountableFile forHostPath(@NotNull final String path) {
        return new MountableFile(new File(path).toURI().toString());
    }