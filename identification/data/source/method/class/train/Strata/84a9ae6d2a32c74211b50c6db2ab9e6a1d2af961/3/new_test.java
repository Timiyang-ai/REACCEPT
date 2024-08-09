  @Test
  public void test_toZonedDateTime_scenario() {
    ValuationZoneTimeDefinition test = ValuationZoneTimeDefinition.of(
        LocalTime.MIDNIGHT, ZONE_ID, LOCAL_TIME_1, LOCAL_TIME_2, LOCAL_TIME_3);
    MarketDataBox<LocalDate> dates = MarketDataBox.ofScenarioValues(
        LocalDate.of(2016, 10, 21), LocalDate.of(2016, 10, 22), LocalDate.of(2016, 10, 23));
    MarketDataBox<ZonedDateTime> computed = test.toZonedDateTime(dates);
    MarketDataBox<ZonedDateTime> expected = MarketDataBox.ofScenarioValue(ScenarioArray.of(
        dates.getValue(0).atTime(LOCAL_TIME_1).atZone(ZONE_ID),
        dates.getValue(1).atTime(LOCAL_TIME_2).atZone(ZONE_ID),
        dates.getValue(2).atTime(LOCAL_TIME_3).atZone(ZONE_ID)));
    assertThat(computed).isEqualTo(expected);
  }