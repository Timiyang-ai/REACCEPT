@Override
    public int compareTo(Object o) {
        if (o instanceof VulnerableSoftware) {
            final VulnerableSoftware other = (VulnerableSoftware) o;
            return new CompareToBuilder()
                    .appendSuper(super.compareTo(other))
                    .append(versionStartIncluding, other.versionStartIncluding)
                    .append(versionStartExcluding, other.versionStartExcluding)
                    .append(versionEndIncluding, other.versionEndIncluding)
                    .append(versionEndExcluding, other.versionEndExcluding)
                    .append(this.vulnerable, other.vulnerable)
                    .build();
        } else if (o instanceof Cpe) {
            return super.compareTo(o);
        }
        throw new UnexpectedAnalysisException("Unable to compare " + o.getClass().getCanonicalName());
    }