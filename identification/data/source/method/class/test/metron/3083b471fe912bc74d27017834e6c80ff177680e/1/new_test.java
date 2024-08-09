@Test
  public void testUpdate() throws Exception {

    // setup
    long timestamp = 100;
    definition = JSONUtils.INSTANCE.load(testUpdateProfile, ProfileConfig.class);
    builder = new DefaultProfileBuilder.Builder()
            .withDefinition(definition)
            .withEntity("10.0.0.1")
            .withPeriodDuration(10, TimeUnit.MINUTES)
            .withContext(Context.EMPTY_CONTEXT())
            .build();

    // execute
    int count = 10;
    for(int i=0; i<count; i++) {

      // apply the message
      builder.apply(message, timestamp);

      // advance time
      timestamp += 5;
    }
    Optional<ProfileMeasurement> m = builder.flush();
    assertTrue(m.isPresent());

    // validate that x=0, y=0 then x+=1, y+=2 for each message
    assertEquals(count*1 + count*2, (int) convert(m.get().getProfileValue(), Integer.class));
  }