public static SecurityPosition ofNet(PositionInfo positionInfo, SecurityId securityId, double netQuantity) {
    double longQuantity = netQuantity >= 0 ? netQuantity : 0;
    double shortQuantity = netQuantity >= 0 ? 0 : -netQuantity;
    return new SecurityPosition(positionInfo, securityId, longQuantity, shortQuantity);
  }