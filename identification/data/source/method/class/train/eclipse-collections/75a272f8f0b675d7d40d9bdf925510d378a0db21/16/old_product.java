public Iterator<T> iterator()
    {
        return new SelectIterator<T>(this.adapted, this.predicate);
    }