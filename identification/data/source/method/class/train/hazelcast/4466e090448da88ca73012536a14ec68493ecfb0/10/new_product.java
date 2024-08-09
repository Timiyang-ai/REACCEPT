public boolean isUnknownOrLessOrEqual(Version version) {
        return isUnknown() || compareTo(version) <= 0;
    }