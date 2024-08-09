@Before
  public void setUp() {
    datastore = DatastoreOptions.builder()
        .projectId(PROJECT_ID)
        .namespace("ghijklmnop") // for namespace metadata query
        .host("http://localhost:" + PORT)
        .build()
        .service();
    StructuredQuery<Key> query = Query.keyQueryBuilder().build();
    QueryResults<Key> result = datastore.run(query);
    datastore.delete(Iterators.toArray(result, Key.class));
    keyFactory = datastore.newKeyFactory().kind("Task");
    taskKey = keyFactory.newKey("some-arbitrary-key");
    testEntity = Entity.builder(taskKey, TEST_FULL_ENTITY).build();
    Calendar startDateCalendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
    startDateCalendar.set(1990, 1, 1);
    startDate = DateTime.copyFrom(startDateCalendar);
    Calendar endDateCalendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
    endDateCalendar.set(2000, 1, 1);
    endDate = DateTime.copyFrom(endDateCalendar);
    Calendar includedCalendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
    includedCalendar.set(1999, 12, 31);
    includedDate = DateTime.copyFrom(includedCalendar);
  }