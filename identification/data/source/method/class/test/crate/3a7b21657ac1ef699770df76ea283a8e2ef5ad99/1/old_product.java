public static Version min(Version version1, Version version2) {
        return version1.id < version2.id ? version1 : version2;
    }