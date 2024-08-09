@SuppressWarnings("unchecked")
    static <T> Iterator<T> ofAll(java.util.Iterator<? extends T> iterator) {
        Objects.requireNonNull(iterator, "iterator is null");
        if (iterator instanceof Iterator) {
            return (Iterator<T>) iterator;
        } else {
            return new Iterator<T>() {

                @Override
                public boolean hasNext() {
                    return iterator.hasNext();
                }

                @Override
                public T next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    return iterator.next();
                }
            };
        }
    }