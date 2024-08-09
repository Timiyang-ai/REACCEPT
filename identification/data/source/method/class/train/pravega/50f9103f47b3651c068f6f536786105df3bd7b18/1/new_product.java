public static void checkNotClosed(boolean closed, Object targetObject) throws ObjectClosedException {
        if (closed) {
            throw new ObjectClosedException(targetObject);
        }
    }