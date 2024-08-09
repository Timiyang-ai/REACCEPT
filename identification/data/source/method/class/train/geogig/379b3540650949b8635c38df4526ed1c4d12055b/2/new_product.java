public Optional<NodeRef> find(final RevTree parent, final String parentPath,
            final String childPath) {

        checkNotNull(parent, "parent");
        checkNotNull(parentPath, "parentPath");
        checkNotNull(childPath, "childPath");
        checkArgument(parentPath.isEmpty()
                || parentPath.charAt(parentPath.length() - 1) != PATH_SEPARATOR);
        checkArgument(!childPath.isEmpty(), "empty child path: '%s/%s'", parentPath, childPath);
        checkArgument(childPath.charAt(childPath.length() - 1) != PATH_SEPARATOR);

        checkArgument(parentPath.isEmpty() || childPath.startsWith(parentPath + PATH_SEPARATOR));

        final List<String> parentSteps = Lists.newArrayList(Splitter.on(PATH_SEPARATOR)
                .omitEmptyStrings().split(parentPath));
        List<String> childSteps = Lists.newArrayList(Splitter.on(PATH_SEPARATOR).split(childPath));
        childSteps = childSteps.subList(parentSteps.size(), childSteps.size());

        RevTree subTree = parent;
        ObjectId metadataId = ObjectId.NULL;
        for (int i = 0; i < childSteps.size() - 1; i++) {
            String directChildName = childSteps.get(i);
            Optional<Node> subtreeRef = getDirectChild(subTree, directChildName, 0);
            if (!subtreeRef.isPresent()) {
                return Optional.absent();
            }
            metadataId = subtreeRef.get().getMetadataId().or(ObjectId.NULL);
            subTree = objectDb.get(subtreeRef.get().getObjectId(), RevTree.class);
        }
        final String childName = childSteps.get(childSteps.size() - 1);
        Optional<Node> node = getDirectChild(subTree, childName, 0);
        NodeRef result = null;
        if (node.isPresent()) {
            String nodeParentPath = NodeRef.parentPath(childPath);
            result = new NodeRef(node.get(), nodeParentPath, node.get().getMetadataId()
                    .or(metadataId));
        }
        return Optional.fromNullable(result);
    }