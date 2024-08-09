private Entity getApplicationInfo( EntityManagerFactory emf, UUID appId ) throws Exception {

        UUID mgmtAppId = emf.getManagementAppId();
        EntityManager rootEm = emf.getEntityManager( mgmtAppId );

        final Results applicationInfoResults = rootEm.searchCollection(
            new SimpleEntityRef("application", mgmtAppId), "application_infos",
            Query.fromQL("select * where applicationId=" + appId.toString()));

        return applicationInfoResults.getEntity();
    }