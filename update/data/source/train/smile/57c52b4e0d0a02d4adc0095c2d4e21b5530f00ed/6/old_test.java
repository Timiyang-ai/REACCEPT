@Test
    public void testGetPathToRoot() {
        System.out.println("getPathToRoot");
        LinkedList<Concept> expResult = new LinkedList<Concept>();
        expResult.addFirst(instance.getRoot());
        expResult.addFirst(ad);
        expResult.addFirst(a);
        expResult.addFirst(c);
        expResult.addFirst(f);
        List<Concept> result = f.getPathToRoot();
        assertEquals(expResult, result);
    }