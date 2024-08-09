public static GenericSecurityPosition ofNet(GenericSecurity security, long netQuantity) {
    return ofNet(PositionInfo.empty(), security, netQuantity);
  }