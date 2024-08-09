@SuppressWarnings("varargs")
    @SafeVarargs // Iterating over an array is safe.
    public static <K> Comparator<K> comparing(Function<K, ? extends Comparable<?>>... methods) {
        return (a, b) -> {
            for (Function<K, ? extends Comparable<?>> method : methods) {
                @SuppressWarnings(value = "unchecked")
                final Function<K, ? extends Comparable<Object>> m
                    = (Function<K, ? extends Comparable<Object>>) method;

                final Comparable<Object> ac = m.apply(a);
                final Comparable<Object> bc = m.apply(b);
                final int c = ac.compareTo(bc);

                if (c != 0) {
                    return c;
                }
            }

            return 0;
        };
    }