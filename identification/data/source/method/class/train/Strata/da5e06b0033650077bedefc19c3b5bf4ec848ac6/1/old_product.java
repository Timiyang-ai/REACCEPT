public CurrencyAmountArray minus(CurrencyAmount amount) {
    if (!amount.getCurrency().equals(currency)) {
      throw new IllegalArgumentException(Messages.format(
          "Currencies must be equal, this currency is {}, other currency is {}", currency, amount.getCurrency()));
    }
    return CurrencyAmountArray.of(currency, values.plus(amount.getAmount()));
  }