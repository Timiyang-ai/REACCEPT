  @Test public void maxDuration_onlyWithMinDuration() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("maxDuration is only valid with minDuration");

    queryBuilder.maxDuration(0L).build();
  }