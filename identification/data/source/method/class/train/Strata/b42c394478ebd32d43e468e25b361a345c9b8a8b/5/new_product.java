public static GenericSecurityPosition ofNet(GenericSecurity security, double netQuantity) {
    return ofNet(PositionInfo.empty(), security, netQuantity);
  }