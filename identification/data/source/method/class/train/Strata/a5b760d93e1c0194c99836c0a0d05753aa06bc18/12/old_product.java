public CashFlows cashFlows(SwapLeg leg, RatesProvider provider) {
    ExpandedSwapLeg expanded = leg.expand();
    CashFlows cashFlowPeriods = cashFlowPeriodsInternal(expanded, provider);
    CashFlows cashFlowEvents = cashFlowEventsInternal(expanded, provider);
    return cashFlowPeriods.combinedWith(cashFlowEvents);
  }