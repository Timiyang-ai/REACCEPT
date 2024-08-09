@Override
    public void addToGroup(String featureId, String groupName) {
        getTarget().addToGroup(featureId, groupName);
        getCacheManager().evict(featureId);
    }