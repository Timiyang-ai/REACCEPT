@Override
    public Map<String, Feature> readGroup(String groupName) {
        if (groupName == null || groupName.isEmpty()) {
            throw new IllegalArgumentException("groupName cannot be null nor empty");
        }
        if (!existGroup(groupName)) {
            throw new GroupNotFoundException(groupName);
        }
        // Retrieve feature per feature (in-memory, no overhead)
        Map<String, Feature> features = new HashMap<String, Feature>();
        for (String feat : featureGroups.get(groupName)) {
            features.put(feat, this.read(feat));
        }
        return features;
    }