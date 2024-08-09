BlobReadOptions getBlobReadInfo(StoreKey id, EnumSet<StoreGetOptions> getOptions) throws StoreException {
    ConcurrentSkipListMap<Offset, IndexSegment> indexSegments = validIndexSegments;
    IndexValue value = findKey(id, null, IndexEntryType.ANY, indexSegments);
    BlobReadOptions readOptions;
    if (value == null) {
      throw new StoreException("Id " + id + " not present in index " + dataDir, StoreErrorCodes.ID_Not_Found);
    } else if (value.isFlagSet(IndexValue.Flags.Delete_Index)) {
      if (!getOptions.contains(StoreGetOptions.Store_Include_Deleted)) {
        throw new StoreException("Id " + id + " has been deleted in index " + dataDir, StoreErrorCodes.ID_Deleted);
      } else {
        readOptions = getDeletedBlobReadOptions(value, id, indexSegments);
      }
    } else if (isExpired(value) && !getOptions.contains(StoreGetOptions.Store_Include_Expired)) {
      throw new StoreException("Id " + id + " has expired ttl in index " + dataDir, StoreErrorCodes.TTL_Expired);
    } else {
      readOptions = new BlobReadOptions(log, value.getOffset(), value.getSize(), value.getExpiresAtMs(), id,
          value.isFlagSet(IndexValue.Flags.Delete_Index), journal.getCrcOfKey(id));
    }
    return readOptions;
  }