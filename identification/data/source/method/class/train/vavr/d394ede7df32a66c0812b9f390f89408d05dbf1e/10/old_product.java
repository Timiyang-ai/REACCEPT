static <T> List<T> ofAll(Iterable<? extends T> elements) {
        Objects.requireNonNull(elements, "elements is null");
        if (elements instanceof List) {
            @SuppressWarnings("unchecked")
            final List<T> list = (List<T>) elements;
            return list;
        } else if (elements instanceof java.util.List<?>) {
            List<T> result = Nil.instance();
            final java.util.List<? extends T> list = (java.util.List<? extends T>) elements;
            final ListIterator<? extends T> iter = list.listIterator(list.size());
            while (iter.hasPrevious()) {
                result = result.prepend(iter.previous());
            }
            return result;
        } else if (elements instanceof NavigableSet<?>) {
            List<T> result = Nil.instance();
            final Iterator<? extends T> iter = ((NavigableSet<? extends T>) elements).descendingIterator();
            while (iter.hasNext()) {
                result = result.prepend(iter.next());
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