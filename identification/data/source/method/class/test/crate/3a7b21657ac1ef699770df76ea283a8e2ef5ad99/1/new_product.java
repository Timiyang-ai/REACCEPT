public static Version min(Version version1, Version version2) {
        return version1.internalId < version2.internalId ? version1 : version2;
    }