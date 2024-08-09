public Optional<SwapPaymentPeriod> findPaymentPeriod(LocalDate date) {
    return paymentPeriods.stream()
        .filter(period -> period.getStartDate().compareTo(date) < 0 && date.compareTo(period.getEndDate()) <= 0)
        .findFirst();
  }