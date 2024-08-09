public <E> CriteriaQuery between(String propName, Comparable<E> from, Comparable<E> to) {
        criterion = criterion.and(Criteria.between(propName, from, to));
        return this;
    }