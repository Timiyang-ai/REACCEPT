public boolean isLessThan(Version version) {
        return !isUnknown() && compareTo(version) < 0;
    }