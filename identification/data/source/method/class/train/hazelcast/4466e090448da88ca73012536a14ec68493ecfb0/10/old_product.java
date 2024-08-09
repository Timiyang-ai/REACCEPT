public boolean isUnknownLessOrEqual(Version version) {
        return isUnknown() || compareTo(version) <= 0;
    }