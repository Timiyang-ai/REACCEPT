public static CatalogResolver catalogResolver(CatalogFeatures features, URI... uris) {
        Catalog catalog = catalog(features, uris);
        return new CatalogResolverImpl(catalog);
    }