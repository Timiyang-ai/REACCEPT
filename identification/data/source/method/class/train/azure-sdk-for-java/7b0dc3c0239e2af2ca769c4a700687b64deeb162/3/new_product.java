public Mono<CosmosConflictResponse> read(CosmosConflictRequestOptions options){
        if(options == null){
            options = new CosmosConflictRequestOptions();
        }
        RequestOptions requestOptions = options.toRequestOptions();
        return this.container.getDatabase()
                .getDocClientWrapper()
                .readConflict(getLink(), requestOptions)
                .map(response -> new CosmosConflictResponse(response, container))
                .single();

    }