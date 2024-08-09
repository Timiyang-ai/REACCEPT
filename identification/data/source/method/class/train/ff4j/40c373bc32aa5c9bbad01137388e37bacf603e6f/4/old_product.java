@Override
    public Map<String, Feature> readGroup(String groupName) {
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