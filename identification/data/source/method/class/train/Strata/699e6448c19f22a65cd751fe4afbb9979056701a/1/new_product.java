public Optional<CurrencyAmount> findNotional(LocalDate date) {
    SwapPaymentPeriod paymentPeriod;

    if (!date.isAfter(paymentPeriods.get(0).getStartDate())) {
      // Use the first payment period if the date is before the start
      paymentPeriod = paymentPeriods.get(0);
    } else if (date.isAfter(paymentPeriods.get(paymentPeriods.size() - 1).getEndDate())) {
      // Use the last payment period if the date is after the end
      paymentPeriod = paymentPeriods.get(paymentPeriods.size() - 1);
    } else {
      paymentPeriod = findPaymentPeriod(date).get();
    }
    if (!(paymentPeriod instanceof NotionalPaymentPeriod)) {
      return Optional.empty();
    }
    return Optional.of(((NotionalPaymentPeriod) paymentPeriod).getNotionalAmount());
  }