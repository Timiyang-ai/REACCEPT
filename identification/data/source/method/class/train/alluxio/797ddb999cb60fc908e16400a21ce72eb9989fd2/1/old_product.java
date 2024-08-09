public void add(Supplier<JournalContext> ctx, String path, Map<PropertyKey, String> properties) {
    try (LockResource r = new LockResource(mLock.writeLock())) {
      if (!properties.isEmpty()) {
        Map<String, String> newProperties = mState.getProperties(path);
        properties.forEach((key, value) -> newProperties.put(key.getName(), value));
        mState.applyAndJournal(ctx, PathPropertiesEntry.newBuilder().setPath(path)
            .putAllProperties(newProperties).build());
      }
    }
  }