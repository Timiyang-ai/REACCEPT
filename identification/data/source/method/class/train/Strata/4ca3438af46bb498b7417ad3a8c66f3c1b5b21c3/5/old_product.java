public PointSensitivities parSpreadSensitivity(FraProduct product, RatesProvider provider) {
    return forwardRateSensitivity(product.expand(), provider).build();
  }