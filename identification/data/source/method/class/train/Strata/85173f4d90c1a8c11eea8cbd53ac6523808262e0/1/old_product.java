@SuppressWarnings({"rawtypes", "unchecked"})
  public CurveSensitivities mergedWith(CurveSensitivities other) {
    PortfolioItemInfo combinedInfo = info;
    if (!info.getId().isPresent() && other.info.getId().isPresent()) {
      combinedInfo = combinedInfo.withId(other.info.getId().get());
    }
    for (AttributeType attrType : other.info.getAttributeTypes()) {
      if (!combinedInfo.getAttributeTypes().contains(attrType)) {
        combinedInfo = combinedInfo.withAttribute(attrType, other.info.getAttribute(attrType));
      }
    }
    return new CurveSensitivities(combinedInfo, mergedWith(other.typedSensitivities).getTypedSensitivities());
  }