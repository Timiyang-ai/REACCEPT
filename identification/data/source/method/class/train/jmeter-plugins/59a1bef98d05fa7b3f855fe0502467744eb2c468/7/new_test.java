@Test
    public void testGetFlightTime() {
        System.out.println("getFlightTime");
        SteppingThreadGroup instance = new SteppingThreadGroup();
        String expResult = "";
        String result = instance.getFlightTime();
        assertEquals(expResult, result);
    }