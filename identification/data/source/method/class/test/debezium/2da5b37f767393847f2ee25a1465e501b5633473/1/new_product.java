public Map<String, ?> offset() {
        Map<String, Object> map = new HashMap<>();
        if (serverId != 0) map.put(SERVER_ID_KEY, serverId);
        if (binlogTimestampSeconds != 0) map.put(TIMESTAMP_KEY, binlogTimestampSeconds);
        if (gtidSet != null) {
            map.put(GTID_SET_KEY, gtidSet);
        }
        map.put(BINLOG_FILENAME_OFFSET_KEY, binlogFilename);
        map.put(BINLOG_POSITION_OFFSET_KEY, binlogPosition);
        map.put(BINLOG_EVENT_ROW_NUMBER_OFFSET_KEY, eventRowNumber);
        if (isSnapshotInEffect()) {
            map.put(SNAPSHOT_KEY, true);
        }
        return map;
    }