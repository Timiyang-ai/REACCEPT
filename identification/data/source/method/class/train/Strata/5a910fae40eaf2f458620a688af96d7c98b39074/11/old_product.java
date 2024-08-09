@Override
  public ResolvedCapitalIndexedBondTrade resolve(ReferenceData refData) {
    ResolvedCapitalIndexedBond resolvedProduct = product.resolve(refData);
    LocalDate settlementDate = info.getSettlementDate().get();
    double accruedInterest = resolvedProduct.accruedInterest(settlementDate) / product.getNotional();
    if (settlementDate.isBefore(resolvedProduct.getStartDate())) {
      throw new IllegalArgumentException("Settlement date must not be before bond starts");
    }
    PaymentPeriod settlement;
    if (product.getYieldConvention().equals(CapitalIndexedBondYieldConvention.INDEX_LINKED_FLOAT)) {
      settlement = KnownAmountPaymentPeriod.of(
          Payment.of(product.getCurrency(),
              -product.getNotional() * quantity * (price + accruedInterest), settlementDate),
          SchedulePeriod.of(
              resolvedProduct.getStartDate(),
              settlementDate,
              product.getAccrualSchedule().getStartDate(),
              settlementDate));
    } else {
      RateObservation rateObservation =
          product.getRateCalculation().createRateObservation(settlementDate, product.getStartIndexValue());
      settlement = CapitalIndexedBondPaymentPeriod.builder()
          .startDate(resolvedProduct.getStartDate())
          .unadjustedStartDate(product.getAccrualSchedule().getStartDate())
          .endDate(settlementDate)
          .rateObservation(rateObservation)
          .currency(product.getCurrency())
          .notional(-product.getNotional() * quantity * (price + accruedInterest))
          .realCoupon(1d)
          .build();
    }

    return ResolvedCapitalIndexedBondTrade.builder()
        .info(info)
        .product(resolvedProduct)
        .quantity(quantity)
        .price(price)
        .settlement(settlement)
        .build();
  }