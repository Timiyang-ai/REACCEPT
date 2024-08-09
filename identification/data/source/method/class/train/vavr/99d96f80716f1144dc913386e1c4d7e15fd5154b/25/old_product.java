@SuppressWarnings("unchecked")
    static <T> Iterator<T> ofAll(java.util.Iterator<? extends T> iterator) {
        Objects.requireNonNull(iterator, "iterator is null");
        if (iterator instanceof Iterator) {
            return (Iterator<T>) iterator;
        } else {
            return new AbstractIterator<T>() {

                @Override
                public boolean hasNext() {
                    return iterator.hasNext();
                }

                @Override
                public T getNext() {
                    return iterator.next();
                }
            };
        }
    }