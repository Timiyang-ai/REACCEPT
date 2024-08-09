public String getName(){
        return getMetadataFirstValue(MetadataSchema.DC_SCHEMA, "title", null, Item.ANY);
    }