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
          Predicates.in(advertisedProviderSet.getSkylarkProviders()),
          requiredProviders
      );
    }