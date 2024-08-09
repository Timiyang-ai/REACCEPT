public boolean isLessOrEqual(Version version) {
        return (!isUnknown() && compareTo(version) <= 0) || (isUnknown() && version.isUnknown());
    }