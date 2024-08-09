public static GenericSecurityPosition ofLongShort(GenericSecurity security, long longQuantity, long shortQuantity) {
    return ofLongShort(PositionInfo.empty(), security, longQuantity, shortQuantity);
  }