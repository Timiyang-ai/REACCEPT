public static void throwIfClosed(boolean closed, Object targetObject) throws ObjectClosedException {
        if (closed) {
            throw new ObjectClosedException(targetObject);
        }
    }