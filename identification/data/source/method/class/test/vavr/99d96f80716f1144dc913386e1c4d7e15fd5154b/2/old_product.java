static <T> Iterator<T> ofAll(java.util.Iterator<? extends T> iterator) {
        Objects.requireNonNull(iterator, "iterator is null");
        return new Impl<T>() {

            @Override
            public boolean hsNext() {
                return iterator.hasNext();
            }

            @Override
            public T getNext() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return iterator.next();
            }
        };
    }