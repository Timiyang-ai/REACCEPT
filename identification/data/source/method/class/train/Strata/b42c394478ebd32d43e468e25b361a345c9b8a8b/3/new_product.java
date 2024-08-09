public static GenericSecurityPosition ofNet(PositionInfo positionInfo, GenericSecurity security, double netQuantity) {
    double longQuantity = netQuantity >= 0 ? netQuantity : 0;
    double shortQuantity = netQuantity >= 0 ? 0 : -netQuantity;
    return new GenericSecurityPosition(positionInfo, security, longQuantity, shortQuantity);
  }