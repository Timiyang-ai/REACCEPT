static <T> Tree<T> fill(int n, T element) {
        return io.vavr.collection.Collections.fillObject(n, element, empty(), Tree::of);
    }