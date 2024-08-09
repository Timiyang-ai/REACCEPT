public MarketDataBox<ZonedDateTime> toZonedDateTime(MarketDataBox<LocalDate> dates) {
    if (dates.isScenarioValue()) {
      int nScenarios = dates.getScenarioCount();
      List<ZonedDateTime> zonedDateTimes = IntStream.range(0, nScenarios)
          .mapToObj(i -> dates.getValue(i).atTime(localTimes.get(i)).atZone(zoneId))
          .collect(Collectors.toList());
      return MarketDataBox.ofScenarioValues(zonedDateTimes);
    }
    // TODO check localTimes size
    ZonedDateTime zonedDateTime = dates.getSingleValue().atTime(localTimes.get(0)).atZone(zoneId);
    return MarketDataBox.ofSingleValue(zonedDateTime);
  }