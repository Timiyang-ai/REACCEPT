    @DataProvider
    public static Object[][] resideInAPackage_rules() {
        String thePackage = ArchRule.class.getPackage().getName();
        return $$(
                $(classes().should().resideInAPackage(thePackage), thePackage),
                $(classes().should(ArchConditions.resideInAPackage(thePackage)), thePackage)
        );
    }