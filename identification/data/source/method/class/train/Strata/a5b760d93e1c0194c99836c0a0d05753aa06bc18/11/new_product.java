public double parSpread(ResolvedSwap swap, RatesProvider provider) {
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