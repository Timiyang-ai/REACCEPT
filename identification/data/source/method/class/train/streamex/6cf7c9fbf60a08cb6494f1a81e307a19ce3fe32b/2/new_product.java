public static <T> StreamEx<T> generate(Supplier<T> s) {
        return of(Stream.generate(s));
    }