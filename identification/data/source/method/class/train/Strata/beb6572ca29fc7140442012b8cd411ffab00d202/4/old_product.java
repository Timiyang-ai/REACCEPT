public static SecurityId futureId(
      ExchangeId exchangeId,
      EtdContractCode contractCode,
      YearMonth expiryMonth,
      EtdVariant variant) {

    ArgChecker.notNull(exchangeId, "exchangeId");
    ArgChecker.notNull(contractCode, "contractCode");
    ArgChecker.notNull(expiryMonth, "expiryMonth");
    ArgChecker.isTrue(expiryMonth.getYear() >= 1000 && expiryMonth.getYear() <= 9999, "Invalid expiry year: ", expiryMonth);
    ArgChecker.notNull(variant, "variant");

    String id = FUT_PREFIX +
        exchangeId + SEPARATOR +
        contractCode + SEPARATOR +
        expiryMonth.getYear() +
        ((char) ((expiryMonth.getMonthValue() / 10) + '0')) +
        ((char) ((expiryMonth.getMonthValue() % 10) + '0')) +
        variant.getCode();
    return SecurityId.of(ETD_SCHEME, id);
  }