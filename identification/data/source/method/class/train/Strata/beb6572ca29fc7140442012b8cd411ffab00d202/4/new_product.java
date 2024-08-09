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

    String id = new StringBuilder(40)
        .append(FUT_PREFIX)
        .append(exchangeId)
        .append(SEPARATOR)
        .append(contractCode)
        .append(SEPARATOR)
        .append(expiryMonth.format(YM_FORMAT))
        .append(variant.getCode())
        .toString();
    return SecurityId.of(ETD_SCHEME, id);
  }