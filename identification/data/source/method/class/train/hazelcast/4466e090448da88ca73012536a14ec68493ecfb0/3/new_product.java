public boolean isUnknownGreaterOrEqual(Version version) {
        return isUnknown() || (!version.isUnknown() && compareTo(version) >= 0);
    }