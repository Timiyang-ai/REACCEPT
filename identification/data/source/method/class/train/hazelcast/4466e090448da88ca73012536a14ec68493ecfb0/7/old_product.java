public boolean isUnknownOrLessThan(Version version) {
        return this.isUnknown() || this.compareTo(version) < 0;
    }