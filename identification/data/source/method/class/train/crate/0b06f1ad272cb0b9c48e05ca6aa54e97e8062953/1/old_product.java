@SuppressWarnings("unchecked")
    public IndexRequest prepare(ShardUpsertRequest request, ShardUpsertRequest.Item item, IndexShard indexShard) throws ElasticsearchException {
        final GetResult getResult = indexShard.getService().get(request.type(), item.id(),
                new String[]{RoutingFieldMapper.NAME, ParentFieldMapper.NAME, TTLFieldMapper.NAME},
                true, item.version(), VersionType.INTERNAL, FetchSourceContext.FETCH_SOURCE, false);

        if (!getResult.isExists()) {
            if(item.missingAssignments() != null){
                try {
                    return prepareMissingAssignmentsIndexRequest(request, item);
                } catch (IOException e) {
                    throw new ElasticsearchException("IOException", e);
                }
            }
            throw new DocumentMissingException(new ShardId(indexShard.indexService().index().name(), request.shardId()), request.type(), item.id());
        } else if (item.assignments() == null) {
            throw new DocumentAlreadyExistsException(new ShardId(indexShard.indexService().index().name(), request.shardId()), request.type(), item.id());
        }

        if (getResult.internalSourceRef() == null) {
            // no source, we can't do nothing, through a failure...
            throw new DocumentSourceMissingException(new ShardId(indexShard.indexService().index().name(), request.shardId()), request.type(), item.id());
        }

        Tuple<XContentType, Map<String, Object>> sourceAndContent = XContentHelper.convertToMap(getResult.internalSourceRef(), true);
        final Map<String, Object> updatedSourceAsMap;
        final XContentType updateSourceContentType = sourceAndContent.v1();
        String routing = getResult.getFields().containsKey(RoutingFieldMapper.NAME) ? getResult.field(RoutingFieldMapper.NAME).getValue().toString() : null;
        String parent = getResult.getFields().containsKey(ParentFieldMapper.NAME) ? getResult.field(ParentFieldMapper.NAME).getValue().toString() : null;

        updatedSourceAsMap = sourceAndContent.v2();

        final SymbolToFieldExtractor.Context ctx = new SymbolToFieldExtractor.Context(functions, item.assignments().length);
        Map<String, FieldExtractor> extractors = new HashMap<>(item.assignments().length);
        for (int i = 0; i < request.assignmentsColumns().length; i++) {
            extractors.put(request.assignmentsColumns()[i], SYMBOL_TO_FIELD_EXTRACTOR.convert(item.assignments()[i], ctx));
        }

        Map<String, Object> pathsToUpdate = new HashMap<>(extractors.size());
        for (Map.Entry<String, FieldExtractor> entry : extractors.entrySet()) {
            /**
             * NOTE: mapping isn't applied. So if an Insert was done using the ES Rest Endpoint
             * the data might be returned in the wrong format (date as string instead of long)
             */
            pathsToUpdate.put(entry.getKey(), entry.getValue().extract(getResult));
        }

        updateSourceByPaths(updatedSourceAsMap, pathsToUpdate);

        final IndexRequest indexRequest = Requests.indexRequest(request.index()).type(request.type()).id(item.id()).routing(routing).parent(parent)
                .source(updatedSourceAsMap, updateSourceContentType)
                .routing(item.routing())
                .version(getResult.getVersion());
        indexRequest.operationThreaded(false);
        return indexRequest;
    }