public static <T> String toString(List<T> list, String delimiter) {
        return list.stream()
                .map(s -> s.toString())
                .collect(Collectors.joining(delimiter));
    }