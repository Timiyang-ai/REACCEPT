public Iterator<T> iterator()
    {
        return new SelectIterator<>(this.adapted, this.predicate);
    }