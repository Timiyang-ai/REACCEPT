public static EtdContractSpecId contractSpecId(EtdType type, ExchangeId exchangeId, String contractCode) {
    ArgChecker.notNull(type, "type");
    ArgChecker.notNull(exchangeId, "exchangeId");
    ArgChecker.notEmpty(contractCode, "contractCode");
    switch (type) {
      case FUTURE:
        return EtdContractSpecId.of(ETD_SCHEME, FUT_PREFIX + exchangeId + SEPARATOR + contractCode);
      case OPTION:
        return EtdContractSpecId.of(ETD_SCHEME, OPT_PREFIX + exchangeId + SEPARATOR + contractCode);
      default:
        throw new IllegalArgumentException("Unknown ETD type: " + type);
    }
  }