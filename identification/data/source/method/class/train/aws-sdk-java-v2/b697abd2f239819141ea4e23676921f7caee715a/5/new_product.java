public Map<String, AttributeValue> primaryKeyMap(TableSchema<?> tableSchema) {
        return keyMap(tableSchema, TableMetadata.primaryIndexName());
    }