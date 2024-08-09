@Test
    public void testGetInUserPeriod() {
        System.out.println("getInUserPeriod");
        SteppingThreadGroup instance = new SteppingThreadGroup();
        String expResult = "";
        String result = instance.getInUserPeriod();
        assertEquals(expResult, result);
    }