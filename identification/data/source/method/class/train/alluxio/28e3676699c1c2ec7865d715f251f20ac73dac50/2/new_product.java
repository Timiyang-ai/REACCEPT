public static TieredIdentity fromString(String identityString, AlluxioConfiguration conf)
      throws IOException {
    Set<String> allTiers = Sets.newHashSet(conf.getList(PropertyKey.LOCALITY_ORDER, ","));
    Map<String, String> tiers = new HashMap<>();
    for (String tier : identityString.split(",")) {
      String[] parts = tier.split("=");
      if (parts.length != 2) {
        throw new IOException(String
            .format("Failed to parse tiered identity. The value should be a comma-separated list "
                + "of key=value pairs, but was %s", identityString));
      }
      String key = parts[0].trim();
      if (tiers.containsKey(key)) {
        throw new IOException(String.format(
            "Encountered repeated tier definition for %s when parsing tiered identity from string "
                + "%s",
            key, identityString));
      }
      if (!allTiers.contains(key)) {
        throw new IOException(String.format("Unrecognized tier: %s. The tiers defined by %s are %s",
            key, PropertyKey.LOCALITY_ORDER.toString(), allTiers));
      }
      tiers.put(key, parts[1].trim());
    }
    List<LocalityTier> tieredIdentity = new ArrayList<>();
    for (String localityTier : conf.getList(PropertyKey.LOCALITY_ORDER, ",")) {
      String value = tiers.getOrDefault(localityTier, null);
      tieredIdentity.add(new LocalityTier(localityTier, value));
    }
    return new TieredIdentity(tieredIdentity);
  }