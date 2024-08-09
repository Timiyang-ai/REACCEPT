public static SecurityPosition ofLongShort(SecurityId securityId, long longQuantity, long shortQuantity) {
    return ofLongShort(PositionInfo.empty(), securityId, longQuantity, shortQuantity);
  }