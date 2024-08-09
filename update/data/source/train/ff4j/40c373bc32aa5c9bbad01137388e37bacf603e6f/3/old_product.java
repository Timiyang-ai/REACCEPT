@Override
    public void disableGroup(String groupName) {
        if (!existGroup(groupName)) {
            throw new GroupNotFoundException(groupName);
        }
        for (String feat : featureGroups.get(groupName)) {
            this.disable(feat);
        }
    }