    public static Set<PersistentResource> getRelation(PersistentResource resource, String relation) {
        Optional<FilterExpression> filterExpression =
                resource.getRequestScope().getExpressionForRelation(resource, relation);

        return resource.getRelationCheckedFiltered(relation, filterExpression, Optional.empty(), Optional.empty());
    }