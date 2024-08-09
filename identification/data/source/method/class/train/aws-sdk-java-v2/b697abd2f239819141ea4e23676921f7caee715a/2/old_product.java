public Map<String, AttributeValue> getKeyMap(TableSchema<?> tableSchema, String index) {
        Map<String, AttributeValue> keyMap = new HashMap<>();
        keyMap.put(tableSchema.getTableMetadata().getIndexPartitionKey(index), partitionKeyValue);

        if (sortKeyValue != null) {
            keyMap.put(tableSchema.getTableMetadata().getIndexSortKey(index).orElseThrow(
                () -> new IllegalArgumentException("A sort key value was supplied for an index that does not support "
                                                   + "one. Index: " + index)), sortKeyValue);
        }

        return Collections.unmodifiableMap(keyMap);
    }