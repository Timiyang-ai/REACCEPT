public default CdsTrade toTrade(
      StandardId legalEntityId,
      LocalDate tradeDate,
      LocalDate startDate,
      Tenor tenor,
      BuySell buySell,
      double notional,
      double fixedRate,
      AdjustablePayment upFrontFee,
      ReferenceData refData) {

    LocalDate roll = CdsImmDateLogic.getNextSemiannualRollDate(startDate);
    LocalDate endDate = roll.plus(tenor).minusMonths(3);
    return toTrade(legalEntityId, tradeDate, startDate, endDate, buySell, notional, fixedRate, upFrontFee, refData);
  }