    @DataProvider
    public static Object[][] callConstructor_rules() {
        return $$(
                $(classes().should().callConstructor(ClassWithConstructor.class, String.class)),
                $(classes().should(ArchConditions.callConstructor(ClassWithConstructor.class, String.class))),
                $(classes().should().callConstructor(ClassWithConstructor.class.getName(), String.class.getName())),
                $(classes().should(ArchConditions.callConstructor(ClassWithConstructor.class.getName(), String.class.getName())))
        );
    }