@PublicAPI(usage = ACCESS)
        public static DescribedPredicate<HasType> type(String typeName) {
            return type(GET_NAME.is(equalTo(typeName))).as("type " + typeName);
        }