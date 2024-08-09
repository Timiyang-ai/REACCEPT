public CompletableFuture<DLSN> writeControlRecord(final LogRecord record) {
        record.setControl();
        return write(record);
    }