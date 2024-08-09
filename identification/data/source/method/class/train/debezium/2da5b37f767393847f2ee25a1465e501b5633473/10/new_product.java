public Map<String, ?> offset() {
        assert serverName() != null && dbName != null;
        Map<String, Object> result = new HashMap<>();
        if (timestamp != null) {
            result.put(TIMESTAMP_USEC_KEY, Conversions.toEpochMicros(timestamp));
        }
        if (txId != null) {
            result.put(TXID_KEY, txId);
        }
        if (lsn != null) {
            result.put(LSN_KEY, lsn);
        }
        if (xmin != null) {
            result.put(XMIN_KEY, xmin);
        }
        if (snapshot) {
            result.put(SNAPSHOT_KEY, true);
            result.put(LAST_SNAPSHOT_RECORD_KEY, lastSnapshotRecord);
        }
        return result;
    }