@Test
    public void testMatchesAtLeastThreeLevels() {

        DependencyVersion instance = new DependencyVersion("2.3.16.3");
        DependencyVersion version = new DependencyVersion("2.3.16.4");
        //true tests
        assertEquals(true, instance.matchesAtLeastThreeLevels(version));
        version = new DependencyVersion("2.3");
        assertEquals(true, instance.matchesAtLeastThreeLevels(version));
        //false tests
        version = new DependencyVersion("2.3.16.1");
        assertEquals(false, instance.matchesAtLeastThreeLevels(version));
        version = new DependencyVersion("2");
        assertEquals(false, instance.matchesAtLeastThreeLevels(version));
    }