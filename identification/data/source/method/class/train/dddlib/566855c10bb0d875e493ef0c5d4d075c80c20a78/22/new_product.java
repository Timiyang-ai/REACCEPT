public CriteriaQuery not(QueryCriterion otherCriterion) {
        criterion = criterion.and(Criteria.not(otherCriterion));
        return this;
    }