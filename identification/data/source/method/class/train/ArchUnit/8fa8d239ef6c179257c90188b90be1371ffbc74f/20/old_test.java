    @DataProvider
    public static Object[][] resideInAnyPackage_rules() {
        String firstPackage = ArchRule.class.getPackage().getName();
        String secondPackage = ArchConfiguration.class.getPackage().getName();
        return $$(
                $(classes().should().resideInAnyPackage(firstPackage, secondPackage),
                        new String[]{firstPackage, secondPackage}),
                $(classes().should(ArchConditions.resideInAnyPackage(firstPackage, secondPackage)),
                        new String[]{firstPackage, secondPackage})
        );
    }