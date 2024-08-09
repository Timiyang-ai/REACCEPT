public double parSpread(FraProduct product, RatesProvider provider) {
    ExpandedFra fra = product.expand();
    double forward = forwardRate(fra, provider);
    return forward - fra.getFixedRate();
  }