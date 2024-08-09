public CriteriaQuery and(QueryCriterion... queryCriterions) {
        criterion = criterion.and(criterionBuilder.and(queryCriterions));
        return this;
    }