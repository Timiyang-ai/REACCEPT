public static GenericSecurityPosition ofNet(PositionInfo positionInfo, GenericSecurity security, long netQuantity) {
    long longQuantity = netQuantity >= 0 ? netQuantity : 0;
    long shortQuantity = netQuantity >= 0 ? 0 : Math.negateExact(netQuantity);
    return new GenericSecurityPosition(positionInfo, security, longQuantity, shortQuantity);
  }