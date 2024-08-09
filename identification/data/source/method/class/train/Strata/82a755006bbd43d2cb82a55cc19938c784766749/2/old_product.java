@Override
  public void collectIndices(ImmutableSet.Builder<Index> builder) {
    paymentPeriods.stream().forEach(period -> period.collectIndices(builder));
  }