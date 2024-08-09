static <T> List<T> ofAll(Iterable<? extends T> elements) {
        Objects.requireNonNull(elements, "elements is null");
        if (elements instanceof List) {
            @SuppressWarnings("unchecked")
            final List<T> list = (List<T>) elements;
            return list;
        } else if (elements instanceof ArrayList || elements instanceof Vector) {
            @SuppressWarnings("unchecked")
            final java.util.List<T> indexedList = (java.util.List<T>) elements;
            List<T> result = Nil.instance();
            for (int i = indexedList.size() - 1; i >= 0; i--) {
                final T element = indexedList.get(i);
                result = result.prepend(element);
            }
            return result;
        } else {
            List<T> result = Nil.instance();
            for (T element : elements) {
                result = result.prepend(element);
            }
            return result.reverse();
        }
    }