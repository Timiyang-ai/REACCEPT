public static MountableFile forClasspathResource(@NotNull final String resourceName, Integer mode) {
        return new MountableFile(getClasspathResource(resourceName, new HashSet<>()).toString(), mode);
    }