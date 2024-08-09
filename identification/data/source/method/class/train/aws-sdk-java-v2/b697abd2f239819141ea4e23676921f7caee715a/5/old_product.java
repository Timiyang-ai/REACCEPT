public Map<String, AttributeValue> getPrimaryKeyMap(TableSchema<?> tableSchema) {
        return getKeyMap(tableSchema, TableMetadata.getPrimaryIndexName());
    }