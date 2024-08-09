@Override
    public void enableGroup(String groupName) {
        getTarget().enableGroup(groupName);
        // Cannot know wich feature to work with (exceptional event) : flush cache
        getCacheManager().clear();
    }