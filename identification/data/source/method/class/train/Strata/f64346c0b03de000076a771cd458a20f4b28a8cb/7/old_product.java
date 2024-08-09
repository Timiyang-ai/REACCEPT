@Override
  public BillTrade withPrice(double yield) {
    return new BillTrade(info, product, quantity, yield);
  }