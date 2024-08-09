private Entity getApplicationInfo( final EntityManagerFactory emf, final UUID appId ) throws Exception {

        final ApplicationScope appScope = getApplicationScope( emf.getManagementAppId() );

        final CollectionScope appInfoCollectionScope =
            new CollectionScopeImpl( appScope.getApplication(), appScope.getApplication(),
                CpNamingUtils.getCollectionScopeNameFromCollectionName( CpNamingUtils.APPLICATION_INFOS ));

        final EntityCollectionManager collectionManager =
            entityCollectionManagerFactory.createCollectionManager( appInfoCollectionScope );

        final GraphManager gm = graphManagerFactory.createEdgeManager(appScope);

        String edgeType = CpNamingUtils.getEdgeTypeFromCollectionName( CpNamingUtils.APPLICATION_INFOS );

        Id rootAppId = appScope.getApplication();

        final SimpleSearchByEdgeType simpleSearchByEdgeType =  new SimpleSearchByEdgeType(
            rootAppId, edgeType, Long.MAX_VALUE, SearchByEdgeType.Order.DESCENDING, null);

        // TODO: is there a better way?

        Observable<org.apache.usergrid.persistence.model.entity.Entity> entityObs =
            gm.loadEdgesFromSource( simpleSearchByEdgeType )
                .flatMap(new Func1<Edge, Observable<org.apache.usergrid.persistence.model.entity.Entity>>() {

                    @Override
                    public Observable<org.apache.usergrid.persistence.model.entity.Entity> call(final Edge edge) {

                        final Id appInfoId = edge.getTargetNode();

                        return collectionManager.load(appInfoId)
                            .filter(new Func1<org.apache.usergrid.persistence.model.entity.Entity, Boolean>() {
                                @Override
                                public Boolean call(final org.apache.usergrid.persistence.model.entity.Entity entity) {
                                    if (entity == null) {
                                        logger.warn("Encountered a null application info for id {}", appInfoId);
                                        return false;
                                    }
                                    if ( entity.getId().getUuid().equals( appId )) {
                                        return true;
                                    }
                                    return false;
                                }
                            });
                    }
                });

        // don't expect many applications, so we block

        org.apache.usergrid.persistence.model.entity.Entity applicationInfo =
            entityObs.toBlocking().lastOrDefault(null);

        if ( applicationInfo == null ) {
            return null;
        }

        Class clazz = Schema.getDefaultSchema().getEntityClass(applicationInfo.getId().getType());

        Entity entity = EntityFactory.newEntity(
            applicationInfo.getId().getUuid(), applicationInfo.getId().getType(), clazz );

        entity.setProperties( CpEntityMapUtils.toMap( applicationInfo ) );

        return entity;

//        UUID mgmtAppId = emf.getManagementAppId();
//        EntityManager rootEm = emf.getEntityManager( mgmtAppId );
//
//        final Results applicationInfoResults = rootEm.searchCollection(
//            new SimpleEntityRef("application", mgmtAppId), "application_infos",
//            Query.fromQL("select * where applicationId=" + appId.toString()));
//
//        return applicationInfoResults.getEntity();
    }