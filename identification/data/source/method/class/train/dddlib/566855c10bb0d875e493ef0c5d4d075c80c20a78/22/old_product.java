public CriteriaQuery not(QueryCriterion otherCriterion) {
        criterion = criterion.and(criterionBuilder.not(otherCriterion));
        return this;
    }