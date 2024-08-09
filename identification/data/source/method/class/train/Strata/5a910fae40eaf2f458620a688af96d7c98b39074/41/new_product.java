@Override
  public ResolvedCapitalIndexedBondTrade resolve(ReferenceData refData) {
    ResolvedCapitalIndexedBond resolvedProduct = product.resolve(refData);
    TradeInfo completedInfo = calculateSettlementDate(refData);
    LocalDate settlementDate = completedInfo.getSettlementDate().get();

    double accruedInterest = resolvedProduct.accruedInterest(settlementDate) / product.getNotional();
    if (settlementDate.isBefore(resolvedProduct.getStartDate())) {
      throw new IllegalArgumentException("Settlement date must not be before bond starts");
    }
    BondPaymentPeriod settlement;
    if (product.getYieldConvention().equals(CapitalIndexedBondYieldConvention.GB_IL_FLOAT)) {
      settlement = KnownAmountBondPaymentPeriod.of(
          Payment.of(product.getCurrency(),
              -product.getNotional() * quantity * (price + accruedInterest), settlementDate),
          SchedulePeriod.of(
              resolvedProduct.getStartDate(),
              settlementDate,
              product.getAccrualSchedule().getStartDate(),
              settlementDate));
    } else {
      RateComputation rateComputation = product.getRateCalculation().createRateComputation(settlementDate);
      settlement = CapitalIndexedBondPaymentPeriod.builder()
          .startDate(resolvedProduct.getStartDate())
          .unadjustedStartDate(product.getAccrualSchedule().getStartDate())
          .endDate(settlementDate)
          .rateComputation(rateComputation)
          .currency(product.getCurrency())
          .notional(-product.getNotional() * quantity * (price + accruedInterest))
          .realCoupon(1d)
          .build();
    }

    return ResolvedCapitalIndexedBondTrade.builder()
        .info(completedInfo)
        .product(resolvedProduct)
        .quantity(quantity)
        .price(price)
        .settlement(settlement)
        .build();
  }