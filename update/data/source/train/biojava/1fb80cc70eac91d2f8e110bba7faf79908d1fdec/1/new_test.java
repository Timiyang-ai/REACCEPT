@Test
    public void testToString() {
//        System.out.println("toString");
        ResidueNumber instance = new ResidueNumber("A", 42, ' ');
        String expResult = "42";
        String result = instance.toString();
        assertEquals(expResult, result);

    }