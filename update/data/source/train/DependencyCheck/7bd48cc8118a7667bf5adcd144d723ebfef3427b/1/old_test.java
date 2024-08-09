@Test
    public void testMatchesAtLeastThreeLevels() {

        DependencyVersion instance = new DependencyVersion("1.2.3.4");
        DependencyVersion version = new DependencyVersion("1.2.3.5");
        //true tests
        assertEquals(true, instance.matchesAtLeastThreeLevels(version));
        version = new DependencyVersion("1.2");
        assertEquals(true, instance.matchesAtLeastThreeLevels(version));
        //false tests
        version = new DependencyVersion("1.2.2.5");
        assertEquals(false, instance.matchesAtLeastThreeLevels(version));
        version = new DependencyVersion("2");
        assertEquals(false, instance.matchesAtLeastThreeLevels(version));
    }