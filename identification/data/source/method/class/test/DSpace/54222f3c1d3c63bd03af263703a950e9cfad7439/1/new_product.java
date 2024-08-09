public String getLicenseCollection()
    {
        return getCollectionService().getMetadata(this, "license");
    }