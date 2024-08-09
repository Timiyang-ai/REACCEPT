    @DataProvider
    public static Object[][] callMethod_rules() {
        return testForEach(
                classes().should().callMethod(ClassWithMethod.class, "method", String.class),
                classes().should(ArchConditions.callMethod(ClassWithMethod.class, "method", String.class)),
                classes().should().callMethod(ClassWithMethod.class.getName(), "method", String.class.getName()),
                classes().should(ArchConditions.callMethod(ClassWithMethod.class.getName(), "method", String.class.getName()))
        );
    }