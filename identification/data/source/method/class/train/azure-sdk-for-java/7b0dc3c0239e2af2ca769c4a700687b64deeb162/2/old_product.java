public Mono<CosmosDatabaseResponse> createDatabase(CosmosDatabaseSettings databaseSettings,
            CosmosDatabaseRequestOptions options) {
        if (options == null) {
            options = new CosmosDatabaseRequestOptions();
        }
        Database wrappedDatabase = new Database();
        wrappedDatabase.id(databaseSettings.id());
        return RxJava2Adapter.singleToMono(RxJavaInterop.toV2Single(asyncDocumentClient.createDatabase(wrappedDatabase, options.toRequestOptions()).map(databaseResourceResponse ->
                new CosmosDatabaseResponse(databaseResourceResponse, this)).toSingle()));
    }