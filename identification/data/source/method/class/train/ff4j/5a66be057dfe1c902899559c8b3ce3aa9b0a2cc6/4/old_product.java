@Override
    public void disableGroup(String groupName) {
        // Cannot know wich feature to work with (exceptional event) : flush cache
        getCacheManager().clear();
        getTarget().disableGroup(groupName);
    }