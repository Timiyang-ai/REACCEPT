@Test
    public void testPermutations() {
        logger.info("permutations");
        Collection<List<String>> expResult = new ArrayList<>();
        expResult.add(new ArrayList<>(Arrays.asList("a","b","c")));
        expResult.add(new ArrayList<>(Arrays.asList("b","a","c")));
        expResult.add(new ArrayList<>(Arrays.asList("b","c","a")));
        expResult.add(new ArrayList<>(Arrays.asList("a","c","b")));
        expResult.add(new ArrayList<>(Arrays.asList("c","a","b")));
        expResult.add(new ArrayList<>(Arrays.asList("c","b","a")));
        Collection<List<String>> result = Combinatorics.<String>permutations(new ArrayList<>(Arrays.asList("a","b","c")));
        assertEquals(expResult, result);
    }