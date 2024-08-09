    @DataProvider
    public static Object[][] resideOutsideOfPackage_rules() {
        String thePackage = ArchRule.class.getPackage().getName();
        return $$(
                $(classes().should().resideOutsideOfPackage(thePackage), thePackage),
                $(classes().should(ArchConditions.resideOutsideOfPackage(thePackage)), thePackage)
        );
    }