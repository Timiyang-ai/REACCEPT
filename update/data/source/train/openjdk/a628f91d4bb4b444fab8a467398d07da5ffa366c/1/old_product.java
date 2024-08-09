public static CatalogResolver catalogResolver(CatalogFeatures features, String... paths) {
        Catalog catalog = catalog(features, paths);
        return new CatalogResolverImpl(catalog);
    }