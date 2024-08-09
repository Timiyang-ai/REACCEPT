public static <T, S extends Geometry> RTree<T, S> create() {
        return new Builder().create();
    }