abstract boolean satisfies(
        Predicate<Class<?>> hasNativeProvider,
        Predicate<SkylarkProviderIdentifier> hasSkylarkProvider,
        RequiredProviders requiredProviders,
        @Nullable Builder missingProviders);