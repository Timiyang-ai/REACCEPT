public boolean matchesAtLeastThreeLevels(DependencyVersion version) {
        if (version == null) {
            return false;
        }

        boolean ret = true;
        int max = (this.versionParts.size() < version.versionParts.size())
                ? this.versionParts.size() : version.versionParts.size();

        if (max > 3) {
            max = 3;
        }

        for (int i = 0; i < max; i++) {
            if (this.versionParts.get(i) == null || !this.versionParts.get(i).equals(version.versionParts.get(i))) {
                ret = false;
                break;
            }
        }

        return ret;
    }