public <E> CriteriaQuery between(String propName, Comparable<E> from, Comparable<E> to) {
        addCriterion(criterionBuilder.between(propName, from, to));
        return this;
    }