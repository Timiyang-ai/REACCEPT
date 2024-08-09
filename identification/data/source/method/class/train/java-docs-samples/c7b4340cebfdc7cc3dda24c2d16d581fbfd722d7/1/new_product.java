@Before
  public void setUp() {
    datastore = DatastoreOptions.builder()
        .projectId(PROJECT_ID)
        .namespace("ghijklmnop")
        .host("http://localhost:" + PORT)
        .build()
        .service();
    StructuredQuery<Key> query = Query.keyQueryBuilder().build();
    QueryResults<Key> result = datastore.run(query);
    datastore.delete(Iterators.toArray(result, Key.class));
    keyFactory = datastore.newKeyFactory().kind("Task");
    taskKey = keyFactory.newKey("some-arbitrary-key");
    testEntity = Entity.builder(taskKey, TEST_FULL_ENTITY).build();
    Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
    calendar.set(1990, 1, 1);
    startDate = DateTime.copyFrom(calendar);
    calendar.set(2000, 1, 1);
    endDate = DateTime.copyFrom(calendar);
    calendar.set(1999, 12, 31);
    includedDate = DateTime.copyFrom(calendar);
  }