public void remove(Supplier<JournalContext> ctx, String path, Set<String> keys) {
    try (LockResource r = new LockResource(mLock.writeLock())) {
      Map<String, String> properties = mState.getProperties(path);
      if (!properties.isEmpty()) {
        keys.forEach(key -> properties.remove(key));
        if (properties.isEmpty()) {
          mState.applyAndJournal(ctx, RemovePathPropertiesEntry.newBuilder().setPath(path).build());
        } else {
          mState.applyAndJournal(ctx, PathPropertiesEntry.newBuilder()
              .setPath(path).putAllProperties(properties).build());
        }
        mHash.markOutdated();
      }
    }
  }