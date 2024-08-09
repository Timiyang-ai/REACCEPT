public boolean satisfies(final AdvertisedProviderSet advertisedProviderSet,
        RequiredProviders requiredProviders) {
      if (advertisedProviderSet.canHaveAnyProvider()) {
        return true;
      }
      return satisfies(
          aClass -> advertisedProviderSet.getNativeProviders().contains(aClass),
          Predicates.in(advertisedProviderSet.getSkylarkProviders()),
          requiredProviders);
    }