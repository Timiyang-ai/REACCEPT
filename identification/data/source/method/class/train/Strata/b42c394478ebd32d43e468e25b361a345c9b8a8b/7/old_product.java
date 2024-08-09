public static SecurityPosition ofNet(SecurityId securityId, long netQuantity) {
    return ofNet(PositionInfo.empty(), securityId, netQuantity);
  }