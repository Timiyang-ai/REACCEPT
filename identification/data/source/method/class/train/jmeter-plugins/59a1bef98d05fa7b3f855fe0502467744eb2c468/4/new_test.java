@Test
    public void testGetInUserCount() {
        System.out.println("getInUserCount");
        SteppingThreadGroup instance = new SteppingThreadGroup();
        String expResult = "";
        String result = instance.getInUserCount();
        assertEquals(expResult, result);
    }