@Test
    public void testTest() throws Exception {
        logger.info("test");
        //Example from Dimaki's Non-parametrics notes. It should NOT reject the null hypothesis and return false.
        FlatDataCollection flatDataCollection =  new FlatDataCollection(Arrays.asList(new Object[]{33.4, 33.3, 31.0, 31.4, 33.5, 34.4, 33.7, 36.2, 34.9, 37.0}));
        String cdfMethod = "normalDistribution";
        double aLevel = 0.3;
        boolean expResult = false;
        boolean result = Lilliefors.test(flatDataCollection, cdfMethod, aLevel);
        assertEquals(expResult, result);
    }