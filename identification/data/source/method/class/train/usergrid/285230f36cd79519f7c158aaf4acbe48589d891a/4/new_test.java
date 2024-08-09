    private Entity getApplicationInfo( UUID appId ) throws Exception {
        Map<String, UUID> apps = setup.getEmf().getApplications();
        if(apps.containsValue(appId)){
            return setup.getEmf().getManagementEntityManager().get(appId);
        }else{
            fail("no app " + appId);
            return null;
        }
    }