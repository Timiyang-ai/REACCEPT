public static RawOptionData ofBlackVolatility(
      List<Period> expiries,
      DoubleArray strikes,
      ValueType strikeType,
      DoubleMatrix data,
      Double shift) {

    ArgChecker.isTrue(expiries.size() == data.rowCount(),
        "expiries list should be of the same size as the external data dimension");
    for (int i = 0; i < expiries.size(); i++) {
      ArgChecker.isTrue(strikes.size() == data.columnCount(),
          "strikes should be of the same size as the inner data dimension");
    }
    return new RawOptionData(expiries, strikes, strikeType, data, null, ValueType.BLACK_VOLATILITY, shift);
  }