  @Test
  public void subProperties() {
    MetricsConfig config = new MetricsConfig(mMetricsProps);

    Properties properties = config.getProperties();

    Map<String, Properties> sinkProps =
        MetricsConfig.subProperties(properties, MetricsSystem.SINK_REGEX);
    assertEquals(2, sinkProps.size());
    assertTrue(sinkProps.containsKey("console"));
    assertTrue(sinkProps.containsKey("jmx"));

    Properties consoleProp = sinkProps.get("console");
    assertEquals(3, consoleProp.size());
    assertEquals("alluxio.metrics.sink.ConsoleSink", consoleProp.getProperty("class"));

    Properties jmxProp = sinkProps.get("jmx");
    assertEquals(1, jmxProp.size());
    assertEquals("alluxio.metrics.sink.JmxSink", jmxProp.getProperty("class"));
  }