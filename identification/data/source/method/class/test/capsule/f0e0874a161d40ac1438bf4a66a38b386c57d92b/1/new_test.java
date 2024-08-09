    private static <T extends Comparable<? super T>> List<T> sort(List<T> list) {
        final List<T> c = new ArrayList<>(list);
        Collections.<T>sort(c);
        return c;
    }