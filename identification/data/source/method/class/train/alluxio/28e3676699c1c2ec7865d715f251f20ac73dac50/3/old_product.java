public static TieredIdentity fromString(String identityString) {
    Map<String, String> tiers = new HashMap<>();
    for (String tier : identityString.split(",")) {
      String[] parts = tier.split("=");
      if (parts.length != 2) {
        throw new RuntimeException(String.format(
            "Failed to parse tiered identity. "
                + "The value should be a comma-separated list of key=value pairs, but was %s",
            identityString));
      }
      String key = parts[0].trim();
      if (tiers.containsKey(key)) {
        throw new RuntimeException(String.format(
            "Encountered repeated tier definition for %s when parsing tiered identity from string "
                + "%s",
            key, identityString));
      }
      tiers.put(key, parts[1].trim());
    }
    List<LocalityTier> tieredIdentity = new ArrayList<>();
    for (String localityTier : Configuration.getList(PropertyKey.LOCALITY_ORDER, ",")) {
      String value = tiers.containsKey(localityTier) ? tiers.get(localityTier) : null;
      tieredIdentity.add(new LocalityTier(localityTier, value));
    }
    return new TieredIdentity(tieredIdentity);
  }