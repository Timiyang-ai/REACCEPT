public CashFlows cashFlows(ResolvedSwapLeg leg, RatesProvider provider) {
    CashFlows cashFlowPeriods = cashFlowPeriodsInternal(leg, provider);
    CashFlows cashFlowEvents = cashFlowEventsInternal(leg, provider);
    return cashFlowPeriods.combinedWith(cashFlowEvents);
  }