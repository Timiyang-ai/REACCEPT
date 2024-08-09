public static NavigableMap<String, ZoneRules> getVersions(String zoneId) {
        Jdk8Methods.requireNonNull(zoneId, "zoneId");
        return getProvider(zoneId).provideVersions(zoneId);
    }