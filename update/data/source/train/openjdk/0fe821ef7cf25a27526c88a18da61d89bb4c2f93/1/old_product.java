public static CatalogResolver catalogResolver(CatalogFeatures features, String... path) {
        Catalog catalog = catalog(features, path);
        return new CatalogResolverImpl(catalog);
    }