    @Test
    public void nameMatching_predicate() {
        assertNameMatches("Some.foobar", ".*foo.*").isTrue();
        assertNameMatches("Some.fobar", ".*foo.*").isFalse();
        assertNameMatches("Some.foobar", ".*fob?o*.*").isTrue();
        assertNameMatches("com.tngtech.SomeClass", ".*W.*").isFalse();
        assertNameMatches("com.tngtech.SomeClass", "com.*").isTrue();
        assertNameMatches("com.tngtech.SomeClass", "co\\..*").isFalse();
        assertNameMatches("com.tngtech.SomeClass", ".*Class").isTrue();
        assertNameMatches("com.tngtech.SomeClass", ".*Clas").isFalse();
        assertNameMatches("com.tngtech.SomeClass", ".*\\.S.*s").isTrue();

        assertThat(nameMatching(".*foo")).hasDescription("name matching '.*foo'");
    }