public double parRate(PricingEnvironment env, SwapProduct product) {
    // find fixed leg
    ExpandedSwap swap = product.expand();
    List<ExpandedSwapLeg> legs = swap.getLegs();
    ExpandedSwapLeg fixedLeg = null;
    int fixedLegIndex = 0;
    for (int i = 0; i < legs.size(); i++) {
      if (legs.get(i).getType() == SwapLegType.FIXED) {
        fixedLeg = legs.get(i);
        fixedLegIndex = i;
        break;
      }
    }
    ArgChecker.notNull(fixedLeg, "no fixed leg found");
    @SuppressWarnings("null")
    Currency ccyFixedLeg = fixedLeg.getCurrency();
    // other payments (not fixed leg coupons) converted in fixed leg currency
    ToDoubleBiFunction<PricingEnvironment, PaymentEvent> eventFn = paymentEventPricer::presentValue;
    double otherLegsConvertedPv = 0.0;
    for (int i = 0; i < legs.size(); i++) {
      if (i != fixedLegIndex) {
        double pvLocal = legValue(env, legs.get(i), paymentPeriodPricer::presentValue, paymentEventPricer::presentValue);
        otherLegsConvertedPv += (pvLocal * env.fxRate(legs.get(i).getCurrency(), ccyFixedLeg));
      }
    }
    double fixedLegEventsPv = fixedLeg.getPaymentEvents().stream()
        .filter(p -> !p.getPaymentDate().isBefore(env.getValuationDate()))
        .mapToDouble(e -> eventFn.applyAsDouble(env, e))
        .sum();
    // PVBP
    double pvbpFixedLeg = pvbp(env, fixedLeg);
    // Par rate
    return -(otherLegsConvertedPv + fixedLegEventsPv) / pvbpFixedLeg;
  }