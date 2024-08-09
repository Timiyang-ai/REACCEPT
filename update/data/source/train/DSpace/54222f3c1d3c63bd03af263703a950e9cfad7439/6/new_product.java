@Override
    public String getName(){
        return getBitstreamService().getMetadataFirstValue(this, MetadataSchema.DC_SCHEMA, "title", null, Item.ANY);
    }