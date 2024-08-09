@Test
    public void testGetPathFromRoot() {
        System.out.println("getPathToRoot");
        LinkedList<Concept> expResult = new LinkedList<Concept>();
        expResult.add(instance.getRoot());
        expResult.add(ad);
        expResult.add(a);
        expResult.add(c);
        expResult.add(f);
        List<Concept> result = f.getPathFromRoot();
        assertEquals(expResult, result);
    }