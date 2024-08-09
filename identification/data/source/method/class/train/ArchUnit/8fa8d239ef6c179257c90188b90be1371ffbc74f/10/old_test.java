    @DataProvider
    public static Object[][] accessTargetWhere_rules() {
        return $$(
                $(classes().should().accessTargetWhere(accessTargetIs(ClassWithFieldMethodAndConstructor.class))),
                $(classes().should(ArchConditions.accessTargetWhere(accessTargetIs(ClassWithFieldMethodAndConstructor.class))))
        );
    }