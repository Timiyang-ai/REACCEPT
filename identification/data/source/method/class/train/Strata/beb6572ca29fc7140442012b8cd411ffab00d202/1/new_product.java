public static SecurityId optionId(
      ExchangeId exchangeId,
      EtdContractCode contractCode,
      YearMonth expiryMonth,
      EtdVariant variant,
      int version,
      PutCall putCall,
      double strikePrice) {

    return optionId(exchangeId, contractCode, expiryMonth, variant, version, putCall, strikePrice, null);
  }