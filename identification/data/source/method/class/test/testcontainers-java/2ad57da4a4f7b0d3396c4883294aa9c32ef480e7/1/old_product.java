public static MountableFile forClasspathResource(@NotNull final String resourceName, int mode) {
        return new MountableFile(getClasspathResource(resourceName, new HashSet<>()).toString(), mode);
    }