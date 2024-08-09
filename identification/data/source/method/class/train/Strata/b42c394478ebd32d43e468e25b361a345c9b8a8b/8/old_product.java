public static SecurityPosition ofLongShort(
      PositionInfo positionInfo,
      SecurityId securityId,
      long longQuantity,
      long shortQuantity) {

    return new SecurityPosition(positionInfo, securityId, longQuantity, shortQuantity);
  }