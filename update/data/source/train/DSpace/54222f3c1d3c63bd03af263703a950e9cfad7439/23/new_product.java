public void setLicense(Context context, String license) throws SQLException {
        getCollectionService().setMetadata(context, this, "license", license);
    }