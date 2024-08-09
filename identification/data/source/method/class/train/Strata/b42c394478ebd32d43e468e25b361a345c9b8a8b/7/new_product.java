public static SecurityPosition ofNet(SecurityId securityId, double netQuantity) {
    return ofNet(PositionInfo.empty(), securityId, netQuantity);
  }