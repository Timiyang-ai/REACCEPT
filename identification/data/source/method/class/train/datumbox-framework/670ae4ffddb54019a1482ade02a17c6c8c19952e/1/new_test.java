@Test
    public void testCombinations() {
        logger.info("combinations");
        Set<Set<String>> expResult = new HashSet<>();
        expResult.add(new HashSet<>(Arrays.asList("a","b")));
        expResult.add(new HashSet<>(Arrays.asList("a","c")));
        expResult.add(new HashSet<>(Arrays.asList("b","c")));
        Set<Set<String>> result = Combinatorics.combinations(new HashSet<>(Arrays.asList("a","b","c","a")), 2);
        assertEquals(expResult, result);
    }