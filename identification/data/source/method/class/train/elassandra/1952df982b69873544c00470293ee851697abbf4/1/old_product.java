public static <T> Key<T> get(TypeLiteral<T> typeLiteral,
                                 Class<? extends Annotation> annotationType) {
        return new Key<T>(typeLiteral, strategyFor(annotationType));
    }