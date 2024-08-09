@Override
    public void disableGroup(String groupName) {
        getTarget().disableGroup(groupName);
        // Cannot know wich feature to work with (exceptional event) : flush cache
        getCacheManager().clear();
    }