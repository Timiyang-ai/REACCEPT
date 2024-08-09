static <T> void addListeners(T[] listeners, List<T> container, String method) {
        if (listeners == null) {
            throw methodDoesNotAcceptParameter(method, "null vararg array.");
        }
        if (listeners.length == 0) {
            throw requiresAtLeastOneListener(method);
        }
        for (T listener : listeners) {
            if (listener == null) {
                throw methodDoesNotAcceptParameter(method, "null listeners.");
            }
            container.add(listener);
        }
    }