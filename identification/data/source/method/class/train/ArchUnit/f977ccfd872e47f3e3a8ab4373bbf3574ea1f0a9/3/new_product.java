@Deprecated
        @PublicAPI(usage = ACCESS)
        public static DescribedPredicate<HasType> type(Class<?> type) {
            return adjustDeprecatedDescription(rawType(type));
        }