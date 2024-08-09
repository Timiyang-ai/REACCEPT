public double parSpread(ResolvedSwap swap, RatesProvider provider) {
    // does the fixed leg, if it exists, of the swap have a future value notional
    if (!swap.getLegs(SwapLegType.FIXED).isEmpty()) {
      ResolvedSwapLeg fixedLeg = fixedLeg(swap);
      SwapPaymentPeriod firstPeriod = fixedLeg.getPaymentPeriods().get(0);
      if (firstPeriod instanceof RatePaymentPeriod) {
        RatePaymentPeriod payment = (RatePaymentPeriod) firstPeriod;
        RateAccrualPeriod firstAccrualPeriod = payment.getAccrualPeriods().get(0);
        if (firstAccrualPeriod.getRateComputation() instanceof FixedOvernightCompoundedAnnualRateComputation) {
          FixedOvernightCompoundedAnnualRateComputation fixedOvernightCompoundedAnnualRateComputation
              = (FixedOvernightCompoundedAnnualRateComputation) firstAccrualPeriod.getRateComputation();
          return parRate(swap, provider) - fixedOvernightCompoundedAnnualRateComputation.getRate();
        }
      }
    }
    ResolvedSwapLeg referenceLeg = swap.getLegs().get(0);
    Currency ccyReferenceLeg = referenceLeg.getCurrency();
    // try one payment compounding, typically for inflation swaps
    Triple<Boolean, Integer, Double> fixedCompounded = checkFixedCompounded(referenceLeg);
    if (fixedCompounded.getFirst()) {
      double df = provider.discountFactor(ccyReferenceLeg, referenceLeg.getPaymentPeriods().get(0).getPaymentDate());
      double convertedPv = presentValue(swap, ccyReferenceLeg, provider).getAmount();
      double referenceConvertedPv = legPricer.presentValue(referenceLeg, provider).getAmount();
      double notional = ((RatePaymentPeriod) referenceLeg.getPaymentPeriods().get(0)).getNotional();
      double parSpread =
          Math.pow(-(convertedPv - referenceConvertedPv) / (df * notional) + 1.0d, 1.0d / fixedCompounded.getSecond()) -
              (1.0d + fixedCompounded.getThird());
      return parSpread;

    }
    // In other cases, try the standard multiperiod par spread
    double convertedPv = presentValue(swap, ccyReferenceLeg, provider).getAmount();
    double pvbp = legPricer.pvbp(referenceLeg, provider);
    return -convertedPv / pvbp;
  }