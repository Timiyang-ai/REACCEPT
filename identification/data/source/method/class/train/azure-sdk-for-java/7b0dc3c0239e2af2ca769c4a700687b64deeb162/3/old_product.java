public Mono<CosmosConflictResponse> read(CosmosConflictRequestOptions options){
        if(options == null){
            options = new CosmosConflictRequestOptions();
        }
        RequestOptions requestOptions = options.toRequestOptions();
        return RxJava2Adapter.singleToMono(RxJavaInterop.toV2Single(this.container.getDatabase()
                .getDocClientWrapper()
                .readConflict(getLink(), requestOptions)
                .map(response -> new CosmosConflictResponse(response, container))
                .toSingle()));
                
    }