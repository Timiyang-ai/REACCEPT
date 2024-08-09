public static boolean isEmpty(final Object o) {
        if (o == null) {
            return true;
        }
        if (o instanceof CharSequence) {
            return ((CharSequence) o).length() == 0;
        }
        if (o.getClass().isArray()) {
            return ((Object[]) o).length == 0;
        }
        if (o instanceof Iterable) {
            return !((Iterable<?>) o).iterator().hasNext();
        }
        if (o instanceof Map) {
            return ((Map<?, ?>) o).isEmpty();
        }
        return false;
    }