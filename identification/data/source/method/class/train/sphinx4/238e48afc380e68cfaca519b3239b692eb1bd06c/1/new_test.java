@Test
    public void testGetDefaultInstance() throws PropertyException, InstantiationException {
        Map<String, Object> defaultProps = new HashMap<String, Object>();
        defaultProps.put(DummyComp.PROP_FRONTEND, new DummyFrontEnd());

        DummyComp dc = (DummyComp) ConMan.getDefaultInstance(DummyComp.class, defaultProps);

        Assert.assertEquals(4, dc.getBeamWidth());
        Assert.assertEquals(1.3, dc.getAlpha(), 1E-10);
        Assert.assertTrue(dc.getFrontEnd() != null);
        Assert.assertTrue(dc.getBestASR().equals("sphinx4"));
        Assert.assertTrue(dc.getLogger() != null);
    }