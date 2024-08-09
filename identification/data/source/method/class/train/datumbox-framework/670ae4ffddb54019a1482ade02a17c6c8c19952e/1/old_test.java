@Test
    public void testCombinations() {
        logger.info("combinations");
        Collection<List<String>> expResult = new ArrayList<>();
        expResult.add(new ArrayList<>(Arrays.asList("a","b")));
        expResult.add(new ArrayList<>(Arrays.asList("a","c")));
        expResult.add(new ArrayList<>(Arrays.asList("b","c")));
        Collection<List<String>> result = Combinatorics.combinations(new ArrayList<>(Arrays.asList("a","b","c")), 2);
        assertEquals(expResult, result);
    }