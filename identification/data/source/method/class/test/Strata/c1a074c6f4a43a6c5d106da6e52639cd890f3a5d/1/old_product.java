public PointSensitivityBuilder parSpreadSensitivity(ResolvedSwap swap, RatesProvider provider) {
    ResolvedSwapLeg referenceLeg = swap.getLegs().get(0);
    Currency ccyReferenceLeg = referenceLeg.getCurrency();
    double convertedPv = presentValue(swap, ccyReferenceLeg, provider).getAmount();
    PointSensitivityBuilder convertedPvDr = presentValueSensitivity(swap, ccyReferenceLeg, provider);
    if (referenceLeg.getPaymentPeriods().size() > 1) { // try multiperiod par-spread
      double pvbp = legPricer.pvbp(referenceLeg, provider);
      // Backward sweep
      double convertedPvBar = -1d / pvbp;
      double pvbpBar = convertedPv / (pvbp * pvbp);
      PointSensitivityBuilder pvbpDr = legPricer.pvbpSensitivity(referenceLeg, provider);
      return convertedPvDr.multipliedBy(convertedPvBar).combinedWith(pvbpDr.multipliedBy(pvbpBar));
    }
    SwapPaymentPeriod firstPeriod = referenceLeg.getPaymentPeriods().get(0);
    ArgChecker.isTrue(firstPeriod instanceof RatePaymentPeriod, "PaymentPeriod must be instance of RatePaymentPeriod");
    RatePaymentPeriod payment = (RatePaymentPeriod) firstPeriod;
    if (payment.getAccrualPeriods().size() == 1) { // no compounding
      // PVBP
      double pvbp = legPricer.pvbp(referenceLeg, provider);
      // Backward sweep
      double convertedPvBar = -1d / pvbp;
      double pvbpBar = convertedPv / (pvbp * pvbp);
      PointSensitivityBuilder pvbpDr = legPricer.pvbpSensitivity(referenceLeg, provider);
      return convertedPvDr.multipliedBy(convertedPvBar).combinedWith(pvbpDr.multipliedBy(pvbpBar));
    }
    // try Compounding
    Triple<Boolean, Integer, Double> fixedCompounded = checkFixedCompounded(referenceLeg);
    ArgChecker.isTrue(fixedCompounded.getFirst(),
        "Swap should have a fixed leg and for one payment it should be based on compunding witout spread.");
    double df = provider.discountFactor(ccyReferenceLeg, referenceLeg.getPaymentPeriods().get(0).getPaymentDate());
    PointSensitivityBuilder dfDr = provider.discountFactors(ccyReferenceLeg)
        .zeroRatePointSensitivity(referenceLeg.getPaymentPeriods().get(0).getPaymentDate());
    double referenceConvertedPv = legPricer.presentValue(referenceLeg, provider).getAmount();
    PointSensitivityBuilder referenceConvertedPvDr = legPricer.presentValueSensitivity(referenceLeg, provider);
    double notional = ((RatePaymentPeriod) referenceLeg.getPaymentPeriods().get(0)).getNotional();
    PointSensitivityBuilder DparSpreadDr =
        convertedPvDr.combinedWith(referenceConvertedPvDr.multipliedBy(-1)).multipliedBy(-1.0d / (df * notional))
            .combinedWith(dfDr.multipliedBy((convertedPv - referenceConvertedPv) / (df * df * notional)))
            .multipliedBy(1.0d / fixedCompounded.getSecond() *
                Math.pow(-(convertedPv - referenceConvertedPv) / (df * notional) + 1.0d,
                    1.0d / fixedCompounded.getSecond() - 1.0d));
    return DparSpreadDr;
  }