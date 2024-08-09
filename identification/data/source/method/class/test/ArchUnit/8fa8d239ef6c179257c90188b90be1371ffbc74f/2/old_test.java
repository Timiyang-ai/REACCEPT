    @DataProvider
    public static Object[][] haveNameMatching_rules() {
        String regex = containsPartOfRegex(SomeClass.class.getSimpleName());
        return $$(
                $(classes().should().haveNameMatching(regex), regex),
                $(classes().should(ArchConditions.haveNameMatching(regex)), regex)
        );
    }