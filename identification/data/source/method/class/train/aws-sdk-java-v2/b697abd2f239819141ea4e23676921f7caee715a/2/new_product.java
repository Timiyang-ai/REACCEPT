public Map<String, AttributeValue> keyMap(TableSchema<?> tableSchema, String index) {
        Map<String, AttributeValue> keyMap = new HashMap<>();
        keyMap.put(tableSchema.tableMetadata().indexPartitionKey(index), partitionKeyValue);

        if (sortKeyValue != null) {
            keyMap.put(tableSchema.tableMetadata().indexSortKey(index).orElseThrow(
                () -> new IllegalArgumentException("A sort key value was supplied for an index that does not support "
                                                   + "one. Index: " + index)), sortKeyValue);
        }

        return Collections.unmodifiableMap(keyMap);
    }