public boolean isUnknownOrGreaterThan(Version version) {
        return isUnknown() || (!version.isUnknown() && compareTo(version) > 0);
    }