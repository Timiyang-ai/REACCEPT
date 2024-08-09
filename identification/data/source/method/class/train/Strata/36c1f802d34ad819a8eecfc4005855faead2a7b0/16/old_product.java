public CurrencyAmount presentValue(Payment payment, BaseProvider provider) {
    // duplicated code to avoid looking up in the provider when not necessary
    if (provider.getValuationDate().isAfter(payment.getDate())) {
      return CurrencyAmount.zero(payment.getCurrency());
    }
    DiscountFactors discountFactors = provider.discountFactors(payment.getCurrency());
    return payment.getValue().multipliedBy(discountFactors.discountFactor(payment.getDate()));
  }