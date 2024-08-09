public static GenericSecurityPosition ofLongShort(
      PositionInfo positionInfo,
      GenericSecurity security,
      double longQuantity,
      double shortQuantity) {

    return new GenericSecurityPosition(positionInfo, security, longQuantity, shortQuantity);
  }