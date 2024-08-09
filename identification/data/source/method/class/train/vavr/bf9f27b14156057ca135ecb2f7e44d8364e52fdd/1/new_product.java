@SuppressWarnings("unchecked")
    static <T> Iterator<T> empty() {
        return (Iterator<T>) EmptyIterator.INSTANCE;
    }