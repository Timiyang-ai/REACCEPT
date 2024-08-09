static <T> List<T> ofAll(Iterable<? extends T> elements) {
        Objects.requireNonNull(elements, "elements is null");
        if (elements instanceof List) {
            @SuppressWarnings("unchecked")
            final List<T> list = (List<T>) elements;
            return list;
        } else if (elements instanceof ArrayList) {
            @SuppressWarnings("unchecked")
            final ArrayList<T> arrayList = (ArrayList<T>) elements;
            List<T> result = Nil.instance();
            for (int i = arrayList.size() - 1; i >= 0; i--) {
                final T element = arrayList.get(i);
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