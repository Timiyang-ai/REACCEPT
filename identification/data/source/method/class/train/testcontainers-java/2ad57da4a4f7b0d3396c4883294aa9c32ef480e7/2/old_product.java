public static MountableFile forClasspathResource(@NotNull final String resourceName) {
        return new MountableFile(getClasspathResource(resourceName, new HashSet<>()).toString());
    }