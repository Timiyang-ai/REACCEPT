    @DataProvider
    public static Object[][] callConstructorWhere_rules() {
        return $$(
                $(classes().should().callConstructorWhere(callTargetIs(ClassWithConstructor.class))),
                $(classes().should(ArchConditions.callConstructorWhere(callTargetIs(ClassWithConstructor.class))))
        );
    }