public static SecurityPosition ofLongShort(SecurityId securityId, double longQuantity, double shortQuantity) {
    return ofLongShort(PositionInfo.empty(), securityId, longQuantity, shortQuantity);
  }