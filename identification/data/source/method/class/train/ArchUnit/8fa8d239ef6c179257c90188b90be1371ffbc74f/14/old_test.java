    @DataProvider
    public static Object[][] resideOutsideOfPackages_rules() {
        String firstPackage = ArchRule.class.getPackage().getName();
        String secondPackage = ArchConfiguration.class.getPackage().getName();
        return $$(
                $(classes().should().resideOutsideOfPackages(firstPackage, secondPackage),
                        new String[]{firstPackage, secondPackage}),
                $(classes().should(ArchConditions.resideOutsideOfPackages(firstPackage, secondPackage)),
                        new String[]{firstPackage, secondPackage})
        );
    }