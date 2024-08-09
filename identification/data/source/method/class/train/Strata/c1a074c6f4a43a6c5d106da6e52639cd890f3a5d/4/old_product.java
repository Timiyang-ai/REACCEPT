public PointSensitivityBuilder parSpreadSensitivity(ResolvedSwap swap, RatesProvider provider) {
    // does the fixed leg of the swap, if it exists, have a future value notional
    if (!swap.getLegs(SwapLegType.FIXED).isEmpty()) {
      ResolvedSwapLeg fixedLeg = fixedLeg(swap);
      SwapPaymentPeriod firstPeriod = fixedLeg.getPaymentPeriods().get(0);
      if (firstPeriod instanceof RatePaymentPeriod) {
        RatePaymentPeriod payment = (RatePaymentPeriod) firstPeriod;
        RateAccrualPeriod firstAccrualPeriod = payment.getAccrualPeriods().get(0);
        if (firstAccrualPeriod.getRateComputation() instanceof FixedOvernightCompoundedAnnualRateComputation) {
          return parRateSensitivity(swap, provider);
        }
      }
    }
    ResolvedSwapLeg referenceLeg = swap.getLegs().get(0);
    Currency ccyReferenceLeg = referenceLeg.getCurrency();
    double convertedPv = presentValue(swap, ccyReferenceLeg, provider).getAmount();
    PointSensitivityBuilder convertedPvDr = presentValueSensitivity(swap, ccyReferenceLeg, provider);
    // try one payment compounding, typically for inflation swaps
    Triple<Boolean, Integer, Double> fixedCompounded = checkFixedCompounded(referenceLeg);
    if (fixedCompounded.getFirst()) {
      double df = provider.discountFactor(ccyReferenceLeg, referenceLeg.getPaymentPeriods().get(0).getPaymentDate());
      PointSensitivityBuilder dfDr = provider.discountFactors(ccyReferenceLeg)
          .zeroRatePointSensitivity(referenceLeg.getPaymentPeriods().get(0).getPaymentDate());
      double referenceConvertedPv = legPricer.presentValue(referenceLeg, provider).getAmount();
      PointSensitivityBuilder referenceConvertedPvDr = legPricer.presentValueSensitivity(referenceLeg, provider);
      double notional = ((RatePaymentPeriod) referenceLeg.getPaymentPeriods().get(0)).getNotional();
      PointSensitivityBuilder dParSpreadDr =
          convertedPvDr.combinedWith(referenceConvertedPvDr.multipliedBy(-1)).multipliedBy(-1.0d / (df * notional))
              .combinedWith(dfDr.multipliedBy((convertedPv - referenceConvertedPv) / (df * df * notional)))
              .multipliedBy(1.0d / fixedCompounded.getSecond() *
                  Math.pow(-(convertedPv - referenceConvertedPv) / (df * notional) + 1.0d,
                      1.0d / fixedCompounded.getSecond() - 1.0d));
      return dParSpreadDr;
    }
    double pvbp = legPricer.pvbp(referenceLeg, provider);
    // Backward sweep
    double convertedPvBar = -1d / pvbp;
    double pvbpBar = convertedPv / (pvbp * pvbp);
    PointSensitivityBuilder pvbpDr = legPricer.pvbpSensitivity(referenceLeg, provider);
    return convertedPvDr.multipliedBy(convertedPvBar).combinedWith(pvbpDr.multipliedBy(pvbpBar));
  }