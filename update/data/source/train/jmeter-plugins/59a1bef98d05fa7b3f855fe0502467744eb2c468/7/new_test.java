@Test
    public void testGetOutUserPeriod() {
        System.out.println("getOutUserPeriod");
        SteppingThreadGroup instance = new SteppingThreadGroup();
        String expResult = "";
        String result = instance.getOutUserPeriod();
        assertEquals(expResult, result);
    }