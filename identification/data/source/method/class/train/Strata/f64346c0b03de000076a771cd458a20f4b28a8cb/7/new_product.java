@Override
  public BillTrade withPrice(double price) {
    return BillTrade.builder().info(info).product(product).quantity(quantity).price(price).build();
  }