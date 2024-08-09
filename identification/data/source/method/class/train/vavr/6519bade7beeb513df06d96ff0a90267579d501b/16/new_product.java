public static <T> List<T> fill(int n, T element) {
        return Collections.fillObject(n, element, empty(), List::of);
    }