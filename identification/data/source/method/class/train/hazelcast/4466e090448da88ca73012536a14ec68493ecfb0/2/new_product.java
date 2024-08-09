public boolean isGreaterThan(Version version) {
        return !version.isUnknown() && compareTo(version) > 0;
    }