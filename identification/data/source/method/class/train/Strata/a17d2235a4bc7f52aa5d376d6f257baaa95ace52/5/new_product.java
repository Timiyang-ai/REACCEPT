public default CdsTrade toTrade(
      StandardId legalEntityId,
      LocalDate tradeDate,
      Tenor tenor,
      BuySell buySell,
      double notional,
      double fixedRate,
      AdjustablePayment upFrontFee,
      ReferenceData refData) {

    LocalDate startDate = CdsImmDateLogic.getPrevIMMDate(tradeDate);
    LocalDate roll = CdsImmDateLogic.getNextSemiannualRollDate(tradeDate);
    LocalDate endDate = roll.plus(tenor).minusMonths(3);
    return toTrade(legalEntityId, tradeDate, startDate, endDate, buySell, notional, fixedRate, upFrontFee, refData);
  }