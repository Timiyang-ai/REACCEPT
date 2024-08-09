  @Test
  public void remove() {
    PathProperties properties = new PathProperties();

    // remove from empty properties
    properties.remove(NoopJournalContext.INSTANCE, ROOT, new HashSet<>(Arrays.asList(
        PropertyKey.USER_FILE_READ_TYPE_DEFAULT.getName(),
        PropertyKey.USER_FILE_WRITE_TYPE_DEFAULT.getName())));
    properties.removeAll(NoopJournalContext.INSTANCE, DIR1);
    Assert.assertTrue(properties.get().isEmpty());

    properties.add(NoopJournalContext.INSTANCE, ROOT, READ_CACHE_WRITE_CACHE_THROUGH);
    properties.add(NoopJournalContext.INSTANCE, DIR1, READ_NO_CACHE_WRITE_THROUGH);
    Map<String, Map<String, String>> got = properties.get();
    Assert.assertEquals(2, got.size());
    assertPropertiesEqual(READ_CACHE_WRITE_CACHE_THROUGH, got.get(ROOT));
    assertPropertiesEqual(READ_NO_CACHE_WRITE_THROUGH, got.get(DIR1));

    // remove non-existent paths
    properties.removeAll(NoopJournalContext.INSTANCE, "non-existent");
    properties.remove(NoopJournalContext.INSTANCE, "non-existent", new HashSet<>(Arrays.asList(
        PropertyKey.USER_FILE_READ_TYPE_DEFAULT.getName(),
        PropertyKey.USER_FILE_WRITE_TYPE_DEFAULT.getName())));
    got = properties.get();
    Assert.assertEquals(2, got.size());
    assertPropertiesEqual(READ_CACHE_WRITE_CACHE_THROUGH, got.get(ROOT));
    assertPropertiesEqual(READ_NO_CACHE_WRITE_THROUGH, got.get(DIR1));

    // remove non-existent keys
    properties.remove(NoopJournalContext.INSTANCE, ROOT, new HashSet<>(Arrays.asList(
        PropertyKey.USER_APP_ID.getName())));
    properties.remove(NoopJournalContext.INSTANCE, DIR1, new HashSet<>(Arrays.asList(
        PropertyKey.UNDERFS_S3_BULK_DELETE_ENABLED.getName())));
    got = properties.get();
    Assert.assertEquals(2, got.size());
    assertPropertiesEqual(READ_CACHE_WRITE_CACHE_THROUGH, got.get(ROOT));
    assertPropertiesEqual(READ_NO_CACHE_WRITE_THROUGH, got.get(DIR1));

    // remove existing keys
    properties.remove(NoopJournalContext.INSTANCE, ROOT, new HashSet<>(Arrays.asList(
        PropertyKey.USER_FILE_WRITE_TYPE_DEFAULT.getName())));
    properties.remove(NoopJournalContext.INSTANCE, DIR1, new HashSet<>(Arrays.asList(
        PropertyKey.USER_FILE_READ_TYPE_DEFAULT.getName())));
    got = properties.get();
    Assert.assertEquals(2, got.size());
    assertPropertiesEqual(READ_CACHE, got.get(ROOT));
    assertPropertiesEqual(WRITE_THROUGH, got.get(DIR1));

    // remove existing paths
    properties.removeAll(NoopJournalContext.INSTANCE, ROOT);
    got = properties.get();
    Assert.assertEquals(1, got.size());
    assertPropertiesEqual(WRITE_THROUGH, got.get(DIR1));
    properties.removeAll(NoopJournalContext.INSTANCE, DIR1);
    got = properties.get();
    Assert.assertEquals(0, got.size());
  }