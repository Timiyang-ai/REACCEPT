public double parSpread(ResolvedSwap swap, RatesProvider provider) {
    ResolvedSwapLeg referenceLeg = swap.getLegs().get(0);
    Currency ccyReferenceLeg = referenceLeg.getCurrency();
    if (referenceLeg.getPaymentPeriods().size() > 1) { // try multiperiod par-spread
      double convertedPv = presentValue(swap, ccyReferenceLeg, provider).getAmount();
      double pvbp = legPricer.pvbp(referenceLeg, provider);
      return -convertedPv / pvbp;
    }
    SwapPaymentPeriod firstPeriod = referenceLeg.getPaymentPeriods().get(0);
    ArgChecker.isTrue(firstPeriod instanceof RatePaymentPeriod, "PaymentPeriod must be instance of RatePaymentPeriod");
    RatePaymentPeriod payment = (RatePaymentPeriod) firstPeriod;
    if (payment.getAccrualPeriods().size() == 1) { // no compounding
      double convertedPv = presentValue(swap, ccyReferenceLeg, provider).getAmount();
      // PVBP
      double pvbpFixedLeg = legPricer.pvbp(referenceLeg, provider);
      // Par rate
      return -convertedPv / pvbpFixedLeg;
    }
    // try Compounding
    Triple<Boolean, Integer, Double> fixedCompounded = checkFixedCompounded(referenceLeg);
    ArgChecker.isTrue(fixedCompounded.getFirst(),
        "Swap should have a fixed leg and for one payment it should be based on compunding witout spread.");
    double df = provider.discountFactor(ccyReferenceLeg, referenceLeg.getPaymentPeriods().get(0).getPaymentDate());
    double convertedPv = presentValue(swap, ccyReferenceLeg, provider).getAmount();
    double referenceConvertedPv = legPricer.presentValue(referenceLeg, provider).getAmount();
    double notional = ((RatePaymentPeriod) referenceLeg.getPaymentPeriods().get(0)).getNotional();
    double parSpread =
        Math.pow(-(convertedPv - referenceConvertedPv) / (df * notional) + 1.0d, 1.0d / fixedCompounded.getSecond()) -
            (1.0d + fixedCompounded.getThird());
    return parSpread;
  }