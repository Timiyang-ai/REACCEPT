static Collection<BQModuleProvider> moduleProviderDependencies(Collection<BQModuleProvider> rootSet) {
        return moduleProviderDependencies(rootSet, new HashMap<>())
                .entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .collect(toList());
    }