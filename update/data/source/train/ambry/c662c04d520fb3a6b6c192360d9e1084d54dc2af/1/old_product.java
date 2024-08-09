boolean disableCompactionForBlobStore(BlobStore store) {
      storesDisabledCompaction.add(store);
      return true;
    }