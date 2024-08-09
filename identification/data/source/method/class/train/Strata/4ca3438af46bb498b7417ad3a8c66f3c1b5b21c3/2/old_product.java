public double parRate(FraProduct product, RatesProvider provider) {
    return forwardRate(product.expand(), provider);
  }