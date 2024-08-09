@PublicAPI(usage = ACCESS)
        public static DescribedPredicate<HasType> type(Class<?> type) {
            return type(type.getName());
        }