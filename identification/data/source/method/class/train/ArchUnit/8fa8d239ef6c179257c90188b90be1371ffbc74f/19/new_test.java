    @DataProvider
    public static Object[][] haveNameNotMatching_rules() {
        String regex = containsPartOfRegex(WrongNamedClass.class.getSimpleName());
        return $$(
                $(classes().should().haveNameNotMatching(regex), regex),
                $(classes().should(ArchConditions.haveNameNotMatching(regex)), regex)
        );
    }