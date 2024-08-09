@Override
    public void enableGroup(String groupName) {
        // Cannot know wich feature to work with (exceptional event) : flush cache
        getCacheManager().clear();
        getTarget().enableGroup(groupName);
    }