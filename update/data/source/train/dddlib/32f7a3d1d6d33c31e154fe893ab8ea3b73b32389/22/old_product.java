public CriteriaQuery or(QueryCriterion... queryCriterions) {
        addCriterion(criterionBuilder.or(queryCriterions));
        return this;
    }