    @DataProvider
    public static Object[][] haveSimpleName_rules() {
        return $$(
                $(classes().should().haveSimpleName(SomeClass.class.getSimpleName())),
                $(classes().should(ArchConditions.haveSimpleName(SomeClass.class.getSimpleName())))
        );
    }