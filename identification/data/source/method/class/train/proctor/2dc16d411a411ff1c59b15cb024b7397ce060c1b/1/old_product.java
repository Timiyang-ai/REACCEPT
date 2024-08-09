public EnvironmentVersion getEnvironmentVersion(final String testName) {
        final ConcurrentMap<String, EnvironmentVersion> versions = environmentVersions;
        if(versions != null) {
            return versions.get(testName);
        } else {
            return null;
        }
    }