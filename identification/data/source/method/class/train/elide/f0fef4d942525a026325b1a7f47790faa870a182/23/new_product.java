public PersistentResource getRelation(com.yahoo.elide.request.Relationship relationship, String id) {
        Set<PersistentResource> resources = getRelation(Collections.singletonList(id), relationship);

        if (resources.isEmpty()) {
            return null;
        }
        // If this is an in-memory object (i.e. UUID being created within tx), datastore may not be able to filter.
        // If we get multiple results back, make sure we find the right id first.
        for (PersistentResource resource : resources) {
            if (resource.matchesId(id)) {
                return resource;
            }
        }
        return null;
    }