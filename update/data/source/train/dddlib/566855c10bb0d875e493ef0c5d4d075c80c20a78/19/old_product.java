public <E> CriteriaQuery between(String propName, Comparable<E> from, Comparable<E> to) {
        criterion = criterion.and(criterionBuilder.between(propName, from, to));
        return this;
    }