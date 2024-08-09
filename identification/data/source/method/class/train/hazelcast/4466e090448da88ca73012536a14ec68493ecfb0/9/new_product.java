public boolean isGreaterOrEqual(Version version) {
        return (!version.isUnknown() && compareTo(version) >= 0) || (version.isUnknown() && isUnknown());
    }