public boolean isUnknownOrGreaterOrEqual(Version version) {
        return isUnknown() || (!version.isUnknown() && compareTo(version) >= 0);
    }