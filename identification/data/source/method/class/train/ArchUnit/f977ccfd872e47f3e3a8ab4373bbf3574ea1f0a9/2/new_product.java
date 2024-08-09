@Deprecated
        @PublicAPI(usage = ACCESS)
        public static DescribedPredicate<HasType> type(DescribedPredicate<? super JavaClass> predicate) {
            return adjustDeprecatedDescription(rawType(predicate));
        }