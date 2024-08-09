    @Test
    public void registerMethod_whenDouble() {
        FakeOperatingSystemBean fakeOperatingSystemBean = new FakeOperatingSystemBean();
        registerMethod(metricsRegistry, fakeOperatingSystemBean, "doubleMethod", "doubleMethod");

        DoubleGauge gauge = metricsRegistry.newDoubleGauge("doubleMethod");
        assertEquals(fakeOperatingSystemBean.doubleMethod(), gauge.read(), 0.1);
    }