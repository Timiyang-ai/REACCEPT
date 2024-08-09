    @DataProvider
    public static Object[][] notHaveSimpleName_rules() {
        return $$(
                $(classes().should().notHaveSimpleName(WrongNamedClass.class.getSimpleName())),
                $(classes().should(ArchConditions.notHaveSimpleName(WrongNamedClass.class.getSimpleName())))
        );
    }