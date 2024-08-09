public void setDescription(Context context, String n) throws SQLException {
        getBitstreamService().setMetadataSingleValue(context, this, MetadataSchema.DC_SCHEMA, "description", null, null, n);
    }