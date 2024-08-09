    @DataProvider
    public static Object[][] callCodeUnitWhere_rules() {
        return $$(
                $(classes().should().callCodeUnitWhere(accessTargetIs(ClassWithFieldMethodAndConstructor.class))),
                $(classes().should(ArchConditions.callCodeUnitWhere(accessTargetIs(ClassWithFieldMethodAndConstructor.class))))
        );
    }