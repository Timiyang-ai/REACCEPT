    @DataProvider
    public static Object[][] containNumberOfElements_rules() {
        return $$(
                $(equalTo(999)),
                $(lessThan(0)),
                $(lessThan(1)),
                $(lessThan(2)),
                $(greaterThan(2)),
                $(greaterThan(3)),
                $(greaterThan(999)),
                $(lessThanOrEqualTo(0)),
                $(lessThanOrEqualTo(1)),
                $(greaterThanOrEqualTo(3)),
                $(greaterThanOrEqualTo(999)));
    }