@Override
  public ResolvedCapitalIndexedBondTrade resolve(ReferenceData refData) {
    CapitalIndexedBond product = getProduct();
    ResolvedCapitalIndexedBond resolvedProduct = product.resolve(refData);
    LocalDate settlementDate = tradeInfo.getSettlementDate().get();
    double accruedInterest = resolvedProduct.accruedInterest(settlementDate) / product.getNotional();
    if (settlementDate.isBefore(resolvedProduct.getStartDate())) {
      throw new IllegalArgumentException("Settlement date must not be before bond starts");
    }
    PaymentPeriod settlement;
    if (product.getYieldConvention().equals(CapitalIndexedBondYieldConvention.INDEX_LINKED_FLOAT)) {
      settlement = KnownAmountPaymentPeriod.of(
          Payment.of(product.getCurrency(),
              -product.getNotional() * quantity * (cleanPrice + accruedInterest), settlementDate),
          SchedulePeriod.of(
              resolvedProduct.getStartDate(),
              settlementDate,
              product.getPeriodicSchedule().getStartDate(),
              settlementDate));
    } else {
      RateObservation rateObservation =
          product.getRateCalculation().createRateObservation(settlementDate, product.getStartIndexValue());
      settlement = CapitalIndexedBondPaymentPeriod.builder()
          .startDate(resolvedProduct.getStartDate())
          .unadjustedStartDate(product.getPeriodicSchedule().getStartDate())
          .endDate(settlementDate)
          .rateObservation(rateObservation)
          .currency(product.getCurrency())
          .notional(-product.getNotional() * quantity * (cleanPrice + accruedInterest))
          .realCoupon(1d)
          .build();
    }

    return ResolvedCapitalIndexedBondTrade.builder()
        .tradeInfo(tradeInfo)
        .product(resolvedProduct)
        .securityStandardId(getSecurity().getStandardId())
        .quantity(quantity)
        .settlement(settlement)
        .build();
  }