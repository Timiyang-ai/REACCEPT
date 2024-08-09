boolean controlCompactionForBlobStore(BlobStore store, boolean enable) {
      if (enable) {
        storesDisabledCompaction.remove(store);
      } else {
        storesDisabledCompaction.add(store);
      }
      return true;
    }