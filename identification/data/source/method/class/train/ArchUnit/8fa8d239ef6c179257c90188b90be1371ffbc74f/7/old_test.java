    @DataProvider
    public static Object[][] accessFieldWhere_rules() {
        return $$(
                $(classes().should().getFieldWhere(accessTargetIs(ClassWithField.class)), "get", "gets"),
                $(classes().should(ArchConditions.getFieldWhere(accessTargetIs(ClassWithField.class))), "get", "gets"),
                $(classes().should().setFieldWhere(accessTargetIs(ClassWithField.class)), "set", "sets"),
                $(classes().should(ArchConditions.setFieldWhere(accessTargetIs(ClassWithField.class))), "set", "sets"),
                $(classes().should().accessFieldWhere(accessTargetIs(ClassWithField.class)), "access", "(gets|sets)"),
                $(classes().should(ArchConditions.accessFieldWhere(accessTargetIs(ClassWithField.class))), "access", "(gets|sets)")
        );
    }