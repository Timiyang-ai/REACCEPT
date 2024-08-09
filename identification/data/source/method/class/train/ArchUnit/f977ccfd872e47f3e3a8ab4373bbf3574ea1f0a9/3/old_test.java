    @DataProvider
    public static Object[][] type_predicates() {
        return testForEach(
                HasType.Predicates.rawType(String.class),
                HasType.Predicates.rawType(String.class.getName()),
                HasType.Predicates.rawType(equivalentTo(String.class)),

                HasType.Predicates.type(String.class),
                HasType.Predicates.type(String.class.getName()),
                HasType.Predicates.type(equivalentTo(String.class)));
    }