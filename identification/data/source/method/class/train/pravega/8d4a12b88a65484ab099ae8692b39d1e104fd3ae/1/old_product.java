static Collection<AttributeUpdate> generateInitialTableAttributes() {
        return Collections.singleton(new AttributeUpdate(Attributes.TABLE_INDEX_OFFSET, AttributeUpdateType.None, 0L));
    }