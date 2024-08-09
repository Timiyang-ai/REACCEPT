public void setSource(Context context, String n) throws SQLException {
        getBitstreamService().setMetadataSingleValue(context, this, MetadataSchema.DC_SCHEMA, "source", null, null, n);
    }