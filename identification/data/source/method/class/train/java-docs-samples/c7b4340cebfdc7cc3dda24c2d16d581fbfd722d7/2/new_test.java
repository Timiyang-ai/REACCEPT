  @Before
  public void setUp() {
    datastore = HELPER.getOptions().toBuilder().setNamespace("ghijklmnop").build().getService();
    StructuredQuery<Key> query = Query.newKeyQueryBuilder().build();
    QueryResults<Key> result = datastore.run(query);
    datastore.delete(Iterators.toArray(result, Key.class));
    keyFactory = datastore.newKeyFactory().setKind("Task");
    taskKey = keyFactory.newKey("some-arbitrary-key");
    testEntity = Entity.newBuilder(taskKey, TEST_FULL_ENTITY).build();
    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
    calendar.set(1990, JANUARY, 1);
    startDate = Timestamp.of(calendar.getTime());
    calendar.set(2000, JANUARY, 1);
    endDate = Timestamp.of(calendar.getTime());
    calendar.set(1999, DECEMBER, 31);
    includedDate = Timestamp.of(calendar.getTime());
  }