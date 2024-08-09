public double parSpread(ResolvedFra fra, RatesProvider provider) {
    double forward = forwardRate(fra, provider);
    return forward - fra.getFixedRate();
  }