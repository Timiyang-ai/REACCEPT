public CurrencyAmount presentValue(Payment payment, BaseProvider provider) {
    // duplicated code to avoid looking up in the provider when not necessary
    if (provider.getValuationDate().isAfter(payment.getDate())) {
      return CurrencyAmount.zero(payment.getCurrency());
    }
    double df = provider.discountFactor(payment.getCurrency(), payment.getDate());
    return payment.getValue().multipliedBy(df);
  }