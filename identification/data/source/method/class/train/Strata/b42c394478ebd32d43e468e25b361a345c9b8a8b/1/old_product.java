public static SecurityPosition ofNet(PositionInfo positionInfo, SecurityId securityId, long netQuantity) {
    long longQuantity = netQuantity >= 0 ? netQuantity : 0;
    long shortQuantity = netQuantity >= 0 ? 0 : Math.negateExact(netQuantity);
    return new SecurityPosition(positionInfo, securityId, longQuantity, shortQuantity);
  }