@Override
    public void removeFromGroup(String featureId, String groupName) {
        getCacheManager().evict(featureId);
        getTarget().removeFromGroup(featureId, groupName);
    }