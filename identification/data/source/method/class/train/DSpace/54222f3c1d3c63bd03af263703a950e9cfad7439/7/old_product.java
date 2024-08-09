public void setUserFormatDescription(String desc) throws SQLException {
        setFormat(null);
        setMetadataSingleValue(MetadataSchema.DC_SCHEMA, "format", null, null, desc);
    }