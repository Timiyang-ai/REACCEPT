public static <T> StreamEx<T> generate(Supplier<T> s) {
        return new StreamEx<>(Stream.generate(s), StreamContext.SEQUENTIAL);
    }