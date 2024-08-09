private Entity getApplicationInfo( final UUID appId ) throws Exception {

        final ApplicationScope managementAppScope = getApplicationScope(CpNamingUtils.MANAGEMENT_APPLICATION_ID);
        final EntityCollectionManager managementCollectionManager = entityCollectionManagerFactory.createCollectionManager(managementAppScope);

        Observable<Edge> edgesObservable = getApplicationInfoEdges();
        //get the graph for all app infos
        Observable<org.apache.usergrid.persistence.model.entity.Entity> entityObs =
                edgesObservable.flatMap(edge -> {
                    final Id appInfoId = edge.getTargetNode();
                    return managementCollectionManager
                        .load(appInfoId)
                        .filter( entity ->{
                            //check for app id
                            return  entity != null ? entity.getId().getUuid().equals(appId) : false;
                        });
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

    }