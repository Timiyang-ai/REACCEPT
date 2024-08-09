public boolean isUnknownOrGreaterThan(Version version) {
        return this.isUnknown() || this.compareTo(version) > 0;
    }