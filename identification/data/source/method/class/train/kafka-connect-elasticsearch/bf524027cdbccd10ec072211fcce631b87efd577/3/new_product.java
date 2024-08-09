protected BulkableAction toBulkableAction(IndexableRecord record) {
    // If payload is null, the record was a tombstone and we should delete from the index.
    if (record.payload == null) {
      return toDeleteRequest(record);
    }
    return writeMethod == WriteMethod.INSERT
            ? toIndexRequest(record)
            : toUpdateRequest(record, writeMethod);
  }