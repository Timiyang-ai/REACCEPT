@Test
  public void testGetScorecards() {
    final List<Scorecard> scorecards = service.getScorecards();

    Assert.assertNotNull(scorecards);
    Assert.assertFalse(scorecards.isEmpty());
  }