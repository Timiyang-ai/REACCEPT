public static GenericSecurityPosition ofLongShort(GenericSecurity security, double longQuantity, double shortQuantity) {
    return ofLongShort(PositionInfo.empty(), security, longQuantity, shortQuantity);
  }