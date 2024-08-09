@Test
    public void testRegister() throws Exception {
        LongMetricImpl l = new LongMetricImpl("rtest", "test");

        mreg.register(l);

        assertEquals(l, mreg.findMetric("rtest"));

        l.reset();

        assertEquals(0, l.value());
    }