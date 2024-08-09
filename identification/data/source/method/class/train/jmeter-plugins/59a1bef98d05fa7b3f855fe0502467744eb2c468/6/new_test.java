@Test
    public void testGetThreadGroupDelay() {
        System.out.println("getThreadGroupDelay");
        SteppingThreadGroup instance = new SteppingThreadGroup();
        String expResult = "";
        String result = instance.getThreadGroupDelay();
        assertEquals(expResult, result);
    }