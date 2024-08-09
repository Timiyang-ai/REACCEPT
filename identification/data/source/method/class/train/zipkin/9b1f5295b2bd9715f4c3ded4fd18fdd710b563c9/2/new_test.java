  @Test public void minDuration_mustBePositive() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("minDuration <= 0");

    queryBuilder.minDuration(0L).build();
  }