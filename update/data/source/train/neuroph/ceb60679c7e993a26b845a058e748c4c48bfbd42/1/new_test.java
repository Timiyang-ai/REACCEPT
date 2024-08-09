@Test
    public void testGetProperties() {
        double ylow=6,yhigh=7;
        Step instance = new Step();
        instance.setYHigh(yhigh);
        instance.setYLow(ylow);
        Properties expResult = new Properties();
        expResult.setProperty("transferFunction.yHigh", String.valueOf(yhigh));
        expResult.setProperty("transferFunction.yLow", String.valueOf(ylow));
        Properties result = instance.getProperties();
        assertEquals(expResult, result);
        }