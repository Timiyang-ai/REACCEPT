@Test
  public void subPropertiesTest() {
    MetricsConfig config = new MetricsConfig(mMetricsProps);

    Properties properties = config.getProperties();

    Map<String, Properties> sinkProps = config.subProperties(properties, MetricsSystem.SINK_REGEX);
    Assert.assertEquals(2, sinkProps.size());
    Assert.assertTrue(sinkProps.containsKey("console"));
    Assert.assertTrue(sinkProps.containsKey("jmx"));

    Properties consoleProp = sinkProps.get("console");
    Assert.assertEquals(3, consoleProp.size());
    Assert.assertEquals("alluxio.metrics.sink.ConsoleSink", consoleProp.getProperty("class"));

    Properties jmxProp = sinkProps.get("jmx");
    Assert.assertEquals(1, jmxProp.size());
    Assert.assertEquals("alluxio.metrics.sink.JmxSink", jmxProp.getProperty("class"));
  }