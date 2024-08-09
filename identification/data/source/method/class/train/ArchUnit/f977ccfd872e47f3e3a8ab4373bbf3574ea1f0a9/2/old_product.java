@PublicAPI(usage = ACCESS)
        public static DescribedPredicate<HasType> type(DescribedPredicate<? super JavaClass> predicate) {
            return GET_TYPE.is(predicate).as("type " + predicate.getDescription());
        }