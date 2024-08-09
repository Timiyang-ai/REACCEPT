    @Before
    public void prepare() throws Exception {
        Functions functions = getFunctions();

        charactersIndexUUID = UUIDs.randomBase64UUID();
        partitionIndexUUID = UUIDs.randomBase64UUID();

        IndicesService indicesService = mock(IndicesService.class);
        IndexService indexService = mock(IndexService.class);
        Index charactersIndex = new Index(TABLE_IDENT.indexNameOrAlias(), charactersIndexUUID);
        Index partitionIndex = new Index(PARTITION_INDEX, partitionIndexUUID);

        when(indicesService.indexServiceSafe(charactersIndex)).thenReturn(indexService);
        when(indicesService.indexServiceSafe(partitionIndex)).thenReturn(indexService);
        indexShard = mock(IndexShard.class);
        when(indexService.getShard(0)).thenReturn(indexShard);

        // Avoid null pointer exceptions
        DocTableInfo tableInfo = mock(DocTableInfo.class);
        Schemas schemas = mock(Schemas.class);
        when(tableInfo.columns()).thenReturn(Collections.<Reference>emptyList());
        when(schemas.getTableInfo(any(RelationName.class), eq(Operation.INSERT))).thenReturn(tableInfo);

        transportShardUpsertAction = new TestingTransportShardUpsertAction(
            mock(ThreadPool.class),
            clusterService,
            MockTransportService.createNewService(Settings.EMPTY, Version.ES_V_6_5_1, THREAD_POOL, clusterService.getClusterSettings()),
            mock(SchemaUpdateClient.class),
            mock(TasksService.class),
            indicesService,
            mock(ShardStateAction.class),
            functions,
            schemas,
            mock(IndexNameExpressionResolver.class)
        );
    }