  private static boolean satisfies(AdvertisedProviderSet providers,
      RequiredProviders requiredProviders) {
    boolean result = requiredProviders.isSatisfiedBy(providers);

    assertThat(
            requiredProviders.isSatisfiedBy(
                providers.getNativeProviders()::contains,
                providers.getSkylarkProviders()::contains))
        .isEqualTo(result);
    return result;
  }