public boolean isUnknownGreaterOrEqual(Version version) {
        return this.isUnknown() || this.compareTo(version) >= 0;
    }