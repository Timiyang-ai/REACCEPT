public static SecurityPosition ofLongShort(
      PositionInfo positionInfo,
      SecurityId securityId,
      double longQuantity,
      double shortQuantity) {

    return new SecurityPosition(positionInfo, securityId, longQuantity, shortQuantity);
  }