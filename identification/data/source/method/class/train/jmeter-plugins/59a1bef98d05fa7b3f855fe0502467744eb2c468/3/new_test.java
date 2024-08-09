@Test
    public void testGetOutUserCount() {
        System.out.println("getOutUserCount");
        SteppingThreadGroup instance = new SteppingThreadGroup();
        String expResult = "";
        String result = instance.getOutUserCount();
        assertEquals(expResult, result);
    }