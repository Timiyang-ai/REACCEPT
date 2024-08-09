public default CdsTrade createTrade(
      StandardId legalEntityId,
      LocalDate tradeDate,
      LocalDate startDate,
      Tenor tenor,
      BuySell buySell,
      double notional,
      double fixedRate,
      ReferenceData refData) {

    LocalDate roll = CdsImmDateLogic.getNextSemiAnnualRollDate(startDate);
    LocalDate endDate = roll.plus(tenor).minusMonths(3);
    return createTrade(legalEntityId, tradeDate, startDate, endDate, buySell, notional, fixedRate, refData);
  }