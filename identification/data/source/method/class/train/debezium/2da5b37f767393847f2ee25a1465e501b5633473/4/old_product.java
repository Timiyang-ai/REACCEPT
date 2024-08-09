public Map<String, ?> offset() {
        assert serverName != null && dbName != null;
        Map<String, Object> result = new HashMap<>();
        if (useconds != null) {
            result.put(TIMESTAMP_KEY, useconds);
        }
        if (txId != null) {
            result.put(TXID_KEY, txId);
        }
        if (lsn != null) {
            result.put(LSN_KEY, lsn);
        }
        if (schemaName != null) {
            result.put(SCHEMA_NAME_KEY, schemaName);
        }
        if (tableName != null) {
            result.put(TABLE_NAME_KEY, tableName);
        }
        if (snapshot) {
            result.put(SNAPSHOT_KEY, true);
            result.put(LAST_SNAPSHOT_RECORD_KEY, lastSnapshotRecord);
        }
        return result;
    }