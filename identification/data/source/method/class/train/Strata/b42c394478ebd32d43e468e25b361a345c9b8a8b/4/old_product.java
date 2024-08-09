public static GenericSecurityPosition ofLongShort(
      PositionInfo positionInfo,
      GenericSecurity security,
      long longQuantity,
      long shortQuantity) {

    return new GenericSecurityPosition(positionInfo, security, longQuantity, shortQuantity);
  }