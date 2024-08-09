private static <T> void addListeners(T[] listeners, List<T> container, String method) {
        if (listeners == null) {
            throw methodDoesNotAcceptParameter(method, "null vararg array.");
        }
        for (T listener : listeners) {
            if (listener == null) {
                throw methodDoesNotAcceptParameter(method, "null listeners.");
            }
            container.add(listener);
        }
    }