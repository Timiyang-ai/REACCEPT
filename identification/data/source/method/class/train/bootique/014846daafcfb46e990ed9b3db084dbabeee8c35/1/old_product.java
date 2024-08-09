static Collection<BQModuleProvider> moduleProviderDependencies(Collection<BQModuleProvider> rootSet) {
        return moduleProviderDependencies(rootSet, new HashSet<>());
    }