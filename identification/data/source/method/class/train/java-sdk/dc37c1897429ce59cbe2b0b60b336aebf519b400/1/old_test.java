@Test
  public void testGetScorecards() {
    List<Scorecard> scorecards = service.getScorecards();

    Assert.assertNotNull(scorecards);
    Assert.assertFalse(scorecards.isEmpty());
  }