@Override
    public void enableGroup(String groupName) {
        if (!existGroup(groupName)) {
            throw new GroupNotFoundException(groupName);
        }
        for (String feat : featureGroups.get(groupName)) {
            this.enable(feat);
        }
    }