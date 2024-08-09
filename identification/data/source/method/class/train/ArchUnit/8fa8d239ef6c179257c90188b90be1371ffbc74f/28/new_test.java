    @DataProvider
    public static Object[][] notImplement_rules() {
        return $$(
                $(classes().should().notImplement(Collection.class), List.class, ArrayList.class),
                $(classes().should(ArchConditions.notImplement(Collection.class)), List.class, ArrayList.class),
                $(classes().should().notImplement(Collection.class.getName()), List.class, ArrayList.class),
                $(classes().should(ArchConditions.notImplement(Collection.class.getName())), List.class, ArrayList.class),
                $(classes().should().notImplement(name(Collection.class.getName()).as(Collection.class.getName())), List.class, ArrayList.class),
                $(classes().should(ArchConditions.notImplement(name(Collection.class.getName()).as(Collection.class.getName()))),
                        List.class, ArrayList.class));
    }