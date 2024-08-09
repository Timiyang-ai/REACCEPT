public static ImmutableList<HasAndroidResourceDeps> getAndroidResourceDeps(
      Collection<BuildRule> rules) {
    return getAndroidResourceDeps(rules, /* includeAssetOnlyRules */ false);
  }