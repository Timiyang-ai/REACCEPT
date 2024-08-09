public boolean isUnknownOrLessThan(Version version) {
        return isUnknown() || compareTo(version) < 0;
    }