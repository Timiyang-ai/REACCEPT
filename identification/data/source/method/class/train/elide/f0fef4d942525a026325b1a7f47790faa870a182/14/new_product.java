protected Set<PersistentResource> getRelationChecked(String relationName, Set<Predicate> filters) {
        List<String> relations = dictionary.getRelationships(obj);

        String realName = dictionary.getNameFromAlias(obj, relationName);
        relationName = (realName == null) ? relationName : realName;

        if (relationName == null || relations == null || !relations.contains(relationName)) {
            throw new InvalidAttributeException(relationName, type);
        }


        // check for deny access on relationship to avoid iterating a lazy collection
        checkFieldAwarePermissions(ReadPermission.class, relationName, null, null);
        try {
            // If we cannot read any element of this type, don't try to filter
            requestScope.getPermissionExecutor().checkUserPermissions(
                    dictionary.getParameterizedType(obj, relationName),
                    ReadPermission.class);
        } catch (ForbiddenAccessException e) {
            return Collections.emptySet();
        }

        return (Set) filter(ReadPermission.class, (Set) getRelationUnchecked(relationName, filters));
    }