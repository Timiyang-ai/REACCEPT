public Map<String, ?> offset() {
        if ( isSnapshotInEffect() ) {
            return Collect.hashMapOf(BINLOG_FILENAME_OFFSET_KEY, binlogFilename,
                                     BINLOG_POSITION_OFFSET_KEY, binlogPosition,
                                     BINLOG_EVENT_ROW_NUMBER_OFFSET_KEY, eventRowNumber,
                                     BINLOG_SNAPSHOT_KEY,true);
        }
        return Collect.hashMapOf(BINLOG_FILENAME_OFFSET_KEY, binlogFilename,
                                 BINLOG_POSITION_OFFSET_KEY, binlogPosition,
                                 BINLOG_EVENT_ROW_NUMBER_OFFSET_KEY, eventRowNumber);
    }