@Deprecated
        @PublicAPI(usage = ACCESS)
        public static DescribedPredicate<HasType> type(String typeName) {
            return adjustDeprecatedDescription(rawType(typeName));
        }