@Test
  public void testRowKeys() throws Exception {
    int hoursAgo = 1;

    // setup
    List<Object> groups = Collections.emptyList();
    rowKeyBuilder = new SaltyRowKeyBuilder(saltDivisor, periodDuration, periodUnits);

    // a dummy profile measurement
    long oldest = System.currentTimeMillis() - TimeUnit.HOURS.toMillis(hoursAgo);
    ProfileMeasurement m = new ProfileMeasurement("profile", "entity", oldest, periodDuration, periodUnits);
    m.setValue(22);

    // generate a list of expected keys
    List<byte[]> expectedKeys = new ArrayList<>();
    for  (int i=0; i<(hoursAgo * 4)+1; i++) {

      // generate the expected key
      byte[] rk = rowKeyBuilder.rowKey(m, groups);
      expectedKeys.add(rk);

      // advance to the next period
      ProfilePeriod next = m.getPeriod().next();
      m = new ProfileMeasurement("profile", "entity", next.getStartTimeMillis(), periodDuration, periodUnits);
    }

    // execute
    List<byte[]> actualKeys = rowKeyBuilder.rowKeys(measurement.getProfileName(), measurement.getEntity(), groups, hoursAgo, TimeUnit.HOURS);

    // validate - expectedKeys == actualKeys
    for(int i=0; i<actualKeys.size(); i++) {
      byte[] actual = actualKeys.get(i);
      byte[] expected = expectedKeys.get(i);
      assertThat(actual, equalTo(expected));
    }
  }