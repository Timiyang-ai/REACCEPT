public Set<PersistentResource> getRelationCheckedFiltered(String relationName) {
        return filter(ReadPermission.class, (Set) getRelation(relationName, true));
    }