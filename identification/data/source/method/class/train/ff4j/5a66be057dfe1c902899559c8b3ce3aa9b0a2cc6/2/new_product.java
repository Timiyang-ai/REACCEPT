@Override
    public void removeFromGroup(String featureId, String groupName) {
        getTarget().removeFromGroup(featureId, groupName);
        getCacheManager().evict(featureId);
    }