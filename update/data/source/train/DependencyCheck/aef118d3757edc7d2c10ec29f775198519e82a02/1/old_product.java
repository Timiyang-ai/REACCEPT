@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DependencyVersion other = (DependencyVersion) obj;
        final int max = (this.versionParts.size() < other.versionParts.size())
                ? this.versionParts.size() : other.versionParts.size();
        //TODO steal better version of code from compareTo
        for (int i = 0; i < max; i++) {
            final String thisPart = this.versionParts.get(i);
            final String otherPart = other.versionParts.get(i);
            if (!thisPart.equals(otherPart)) {
                return false;
            }
        }
        if (this.versionParts.size() > max) {
            for (int i = max; i < this.versionParts.size(); i++) {
                if (!"0".equals(this.versionParts.get(i))) {
                    return false;
                }
            }
        }

        if (other.versionParts.size() > max) {
            for (int i = max; i < other.versionParts.size(); i++) {
                if (!"0".equals(other.versionParts.get(i))) {
                    return false;
                }
            }
        }

        /*
         *  if (this.versionParts != other.versionParts && (this.versionParts == null || !this.versionParts.equals(other.versionParts))) {
         *      return false;
         *  }
         */
        return true;
    }