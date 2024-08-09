public CriteriaQuery and(QueryCriterion aCriterion) {
        if (aCriterion == null) {
            return this;
        }
        criterion = criterion.and(aCriterion);
        return this;
    }