private BulkableAction toBulkableAction(IndexableRecord record) {
    // If payload is null, the record was a tombstone and we should delete from the index.
    return record.payload != null ? toIndexRequest(record) : toDeleteRequest(record);
  }