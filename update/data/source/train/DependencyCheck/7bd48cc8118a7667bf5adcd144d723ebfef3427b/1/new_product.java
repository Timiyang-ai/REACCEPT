public boolean matchesAtLeastThreeLevels(DependencyVersion version) {
        if (version == null) {
            return false;
        }
        if (Math.abs(this.versionParts.size() - version.versionParts.size()) >= 3) {
            return false;
        }

        final int max = (this.versionParts.size() < version.versionParts.size())
                ? this.versionParts.size() : version.versionParts.size();

        boolean ret = true;
        for (int i = 0; i < max; i++) {
            String thisVersion = this.versionParts.get(i);
            String otherVersion = version.getVersionParts().get(i);
            if (i >= 3) {
                if (thisVersion.compareToIgnoreCase(otherVersion) >= 0) {
                    ret = false;
                    break;
                }
            } else if (!thisVersion.equals(otherVersion)) {
                ret = false;
                break;
            }
        }

        return ret;
    }