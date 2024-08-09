@Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !(other instanceof NugetPackageReference)) {
            return false;
        }
        final NugetPackageReference o = (NugetPackageReference) other;
        return o.getId().equals(id)
                && o.getVersion().equals(version);
    }