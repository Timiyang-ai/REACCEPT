public CriteriaQuery or(QueryCriterion... queryCriterions) {
        criterion = criterion.and(criterionBuilder.or(queryCriterions));
        return this;
    }