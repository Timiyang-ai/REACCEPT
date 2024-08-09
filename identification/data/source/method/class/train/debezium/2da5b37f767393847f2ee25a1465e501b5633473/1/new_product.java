public Map<String, ?> offset() {
        Map<String, Object> map = new HashMap<>();
        if (binlogGtids != null) {
            map.put(BINLOG_GTID_KEY, binlogGtids.toString());
        }
        if (serverId != 0 ) map.put(SERVER_ID_KEY, serverId);
        if (binlogTs != 0 ) map.put(BINLOG_EVENT_TIMESTAMP_KEY, binlogTs);
        map.put(BINLOG_FILENAME_OFFSET_KEY, binlogFilename);
        map.put(BINLOG_POSITION_OFFSET_KEY, binlogPosition);
        map.put(BINLOG_EVENT_ROW_NUMBER_OFFSET_KEY, eventRowNumber);
        if (isSnapshotInEffect()) {
            map.put(BINLOG_SNAPSHOT_KEY, true);
        }
        return map;
    }