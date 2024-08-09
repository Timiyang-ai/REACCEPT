    @DataProvider
    public static Object[][] accessField_rules() {
        return $$(
                $(classes().should().getField(ClassWithField.class, "field"), "get", "gets"),
                $(classes().should(ArchConditions.getField(ClassWithField.class, "field")), "get", "gets"),
                $(classes().should().getField(ClassWithField.class.getName(), "field"), "get", "gets"),
                $(classes().should(ArchConditions.getField(ClassWithField.class.getName(), "field")), "get", "gets"),
                $(classes().should().setField(ClassWithField.class, "field"), "set", "sets"),
                $(classes().should(ArchConditions.setField(ClassWithField.class, "field")), "set", "sets"),
                $(classes().should().setField(ClassWithField.class.getName(), "field"), "set", "sets"),
                $(classes().should(ArchConditions.setField(ClassWithField.class.getName(), "field")), "set", "sets"),
                $(classes().should().accessField(ClassWithField.class, "field"), "access", "(gets|sets)"),
                $(classes().should(ArchConditions.accessField(ClassWithField.class, "field")), "access", "(gets|sets)"),
                $(classes().should().accessField(ClassWithField.class.getName(), "field"), "access", "(gets|sets)"),
                $(classes().should(ArchConditions.accessField(ClassWithField.class.getName(), "field")), "access", "(gets|sets)")
        );
    }