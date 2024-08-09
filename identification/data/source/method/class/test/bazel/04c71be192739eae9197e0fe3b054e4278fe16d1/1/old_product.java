public boolean satisfies(final AdvertisedProviderSet advertisedProviderSet,
        RequiredProviders requiredProviders) {
      if (advertisedProviderSet.canHaveAnyProvider()) {
        return true;
      }
      return satisfies(
          new Predicate<Class<?>>() {
            @Override
            public boolean apply(Class<?> aClass) {
              return advertisedProviderSet.getNativeProviders().contains(aClass);
            }
          },
          new Predicate<SkylarkProviderIdentifier>() {
            @Override
            public boolean apply(SkylarkProviderIdentifier skylarkProviderIdentifier) {
              if (!skylarkProviderIdentifier.isLegacy()) {
                return false;
              }
              return advertisedProviderSet.getSkylarkProviders()
                  .contains(skylarkProviderIdentifier.getLegacyId());
            }
          },
          requiredProviders
      );
    }