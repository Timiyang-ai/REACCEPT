public void setFilter(SerializablePredicate<T> filter) {
        this.filter = filter;
        refreshAll();
    }