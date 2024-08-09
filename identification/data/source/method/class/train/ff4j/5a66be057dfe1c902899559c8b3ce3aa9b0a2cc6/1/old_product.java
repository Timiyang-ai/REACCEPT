@Override
    public void addToGroup(String featureId, String groupName) {
        getCacheManager().evict(featureId);
        getTarget().addToGroup(featureId, groupName);

    }