public default CdsTrade createTrade(
      StandardId legalEntityId,
      LocalDate tradeDate,
      Tenor tenor,
      BuySell buySell,
      double notional,
      double fixedRate,
      AdjustablePayment upFrontFee,
      ReferenceData refData) {

    LocalDate startDate = CdsImmDateLogic.getPreviousImmDate(tradeDate);
    LocalDate roll = CdsImmDateLogic.getNextSemiAnnualRollDate(tradeDate);
    LocalDate endDate = roll.plus(tenor).minusMonths(3);
    return createTrade(legalEntityId, tradeDate, startDate, endDate, buySell, notional, fixedRate, upFrontFee, refData);
  }