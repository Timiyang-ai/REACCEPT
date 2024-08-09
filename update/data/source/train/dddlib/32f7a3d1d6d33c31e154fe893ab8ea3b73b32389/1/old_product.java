public CriteriaQuery and(QueryCriterion... queryCriterions) {
        addCriterion(criterionBuilder.and(queryCriterions));
        return this;
    }