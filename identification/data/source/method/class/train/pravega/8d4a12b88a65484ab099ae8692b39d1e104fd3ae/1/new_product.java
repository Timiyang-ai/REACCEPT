static Collection<AttributeUpdate> generateInitialTableAttributes() {
        return Arrays.asList(
                new AttributeUpdate(Attributes.TABLE_INDEX_OFFSET, AttributeUpdateType.None, 0L),
                new AttributeUpdate(Attributes.TABLE_ENTRY_COUNT, AttributeUpdateType.None, 0L),
                new AttributeUpdate(Attributes.TABLE_BUCKET_COUNT, AttributeUpdateType.None, 0L));
    }