@Test
    public void testToString() {
//        System.out.println("toString");
        ResidueNumber instance = new ResidueNumber("A", 42, " ");
        String expResult = "Chain:A resNum:42 insCode: ";
        String result = instance.toString();
        assertEquals(expResult, result);

    }