public void setName(Context context, String n) throws SQLException {
        getBitstreamService().setMetadataSingleValue(context, this, MetadataSchema.DC_SCHEMA, "title", null, null, n);
    }