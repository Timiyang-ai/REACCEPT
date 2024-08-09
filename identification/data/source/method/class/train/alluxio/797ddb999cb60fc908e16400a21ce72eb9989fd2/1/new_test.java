  @Test
  public void add() {
    PathProperties properties = new PathProperties();

    // add new properties
    properties.add(NoopJournalContext.INSTANCE, ROOT, READ_CACHE_WRITE_CACHE_THROUGH);
    properties.add(NoopJournalContext.INSTANCE, DIR1, READ_NO_CACHE_WRITE_THROUGH);
    Map<String, Map<String, String>> got = properties.get();
    Assert.assertEquals(2, got.size());
    assertPropertiesEqual(READ_CACHE_WRITE_CACHE_THROUGH, got.get(ROOT));
    assertPropertiesEqual(READ_NO_CACHE_WRITE_THROUGH, got.get(DIR1));

    // overwrite existing properties
    properties.add(NoopJournalContext.INSTANCE, DIR1, READ_CACHE_WRITE_CACHE_THROUGH);
    got = properties.get();
    Assert.assertEquals(2, got.size());
    assertPropertiesEqual(READ_CACHE_WRITE_CACHE_THROUGH, got.get(ROOT));
    assertPropertiesEqual(READ_CACHE_WRITE_CACHE_THROUGH, got.get(DIR1));

    // merge properties
    properties.add(NoopJournalContext.INSTANCE, ROOT, READ_NO_CACHE_WRITE_THROUGH);
    properties.add(NoopJournalContext.INSTANCE, DIR1_NESTED, READ_CACHE_WRITE_CACHE_THROUGH);
    properties.add(NoopJournalContext.INSTANCE, DIR2, READ_NO_CACHE_WRITE_THROUGH);
    got = properties.get();
    Assert.assertEquals(4, got.size());
    assertPropertiesEqual(READ_NO_CACHE_WRITE_THROUGH, got.get(ROOT));
    assertPropertiesEqual(READ_CACHE_WRITE_CACHE_THROUGH, got.get(DIR1));
    assertPropertiesEqual(READ_CACHE_WRITE_CACHE_THROUGH, got.get(DIR1_NESTED));
    assertPropertiesEqual(READ_NO_CACHE_WRITE_THROUGH, got.get(DIR2));
  }