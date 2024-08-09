@Test
    public void testGetProperties() {
        double ylow=6,yhigh=7;
        Step instance = new Step();
        instance.setYHigh(yhigh);
        instance.setYLow(ylow);
        Properties expResult = new Properties();
        expResult.setProperty("transferFunction.yHigh", new Double(yhigh).toString());
        expResult.setProperty("transferFunction.yLow",new Double(ylow).toString());
        Properties result = instance.getProperties();
        assertEquals(expResult, result);
        }