  private List<String> findAllDeviceTokenForVariantIDByCriteria(String variantID,
      List<String> categories, List<String> aliases, List<String> deviceTypes) {
    return findAllDeviceTokenForVariantIDByCriteria(variantID, categories, aliases, deviceTypes,
        false);
  }