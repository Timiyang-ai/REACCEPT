@SuppressWarnings("unchecked")
    static <T> Tree<T> ofAll(Iterable<? extends T> iterable) {
        Objects.requireNonNull(iterable, "iterable is null");
        if (iterable instanceof Tree) {
            return (Tree<T>) iterable;
        } else {
            final io.vavr.collection.List<T> list = io.vavr.collection.List.ofAll(iterable);
            return list.isEmpty() ? Empty.instance() : new Node<>(list.head(), list.tail().map(Tree::of));
        }
    }