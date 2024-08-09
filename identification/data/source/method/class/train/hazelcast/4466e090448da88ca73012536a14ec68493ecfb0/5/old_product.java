public boolean isUnknownLessOrEqual(Version version) {
        return this.isUnknown() || this.compareTo(version) <= 0;
    }