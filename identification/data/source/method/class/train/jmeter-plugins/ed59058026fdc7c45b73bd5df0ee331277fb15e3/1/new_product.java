@Test
    public void testSetChartType()
    {
        System.out.println("setChartType");
        int monitorType = 0;
        AbstractPerformanceMonitoringGui instance = new AbstractPerformanceMonitoringGuiImpl();
        instance.setChartType(monitorType);
    }