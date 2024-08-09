public MarketDataBox<ZonedDateTime> toZonedDateTime(MarketDataBox<LocalDate> dates) {
    ArgChecker.isTrue(dates.getScenarioCount() == localTimes.getScenarioCount(),
        "the number of scenarios must be the same");
    if (dates.isScenarioValue()) {
      int nScenarios = dates.getScenarioCount();
      List<ZonedDateTime> zonedDateTimes = IntStream.range(0, nScenarios)
          .mapToObj(i -> dates.getValue(i).atTime(localTimes.get(i)).atZone(zoneId))
          .collect(Collectors.toList());
      return MarketDataBox.ofScenarioValues(zonedDateTimes);
    }
    ZonedDateTime zonedDateTime = dates.getSingleValue().atTime(localTimes.get(0)).atZone(zoneId);
    return MarketDataBox.ofSingleValue(zonedDateTime);
  }