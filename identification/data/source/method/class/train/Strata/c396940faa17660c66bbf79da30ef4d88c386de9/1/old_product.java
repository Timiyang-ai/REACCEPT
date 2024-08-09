@SuppressWarnings({"rawtypes", "unchecked"})
  public default PortfolioItemInfo combinedWith(PortfolioItemInfo other) {
    PortfolioItemInfo combinedInfo = this;
    if (!combinedInfo.getId().isPresent() && other.getId().isPresent()) {
      combinedInfo = combinedInfo.withId(other.getId().get());
    }
    for (AttributeType attrType : other.getAttributeTypes()) {
      if (!combinedInfo.getAttributeTypes().contains(attrType)) {
        combinedInfo = combinedInfo.withAttribute(attrType, other.getAttribute(attrType));
      }
    }
    return combinedInfo;
  }