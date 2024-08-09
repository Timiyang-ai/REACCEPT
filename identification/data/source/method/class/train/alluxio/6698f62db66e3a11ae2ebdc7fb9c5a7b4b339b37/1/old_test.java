@Test
  public void subPropertiesTest() {
    MetricsConfig config = new MetricsConfig(mMetricsProps);

    Map<String, Properties> propertyCategories = config.getPropertyCategories();
    Assert.assertEquals(3, propertyCategories.size());

    Properties masterProp = config.getInstanceProperties("master");
    Map<String, Properties> sourceProps =
        config.subProperties(masterProp, MetricsSystem.SOURCE_REGEX);
    Assert.assertEquals(1, sourceProps.size());
    Assert.assertEquals("alluxio.metrics.source.JvmSource",
        sourceProps.get("jvm").getProperty("class"));

    Map<String, Properties> sinkProps = config.subProperties(masterProp, MetricsSystem.SINK_REGEX);
    Assert.assertEquals(3, sinkProps.size());
    Assert.assertTrue(sinkProps.containsKey("servlet"));
    Assert.assertTrue(sinkProps.containsKey("console"));
    Assert.assertTrue(sinkProps.containsKey("jmx"));

    Properties servletProp = sinkProps.get("servlet");
    Assert.assertEquals(2, servletProp.size());
    Assert.assertEquals("alluxio.metrics.sink.MetricsServlet", servletProp.getProperty("class"));

    Properties consoleProp = sinkProps.get("console");
    Assert.assertEquals(3, consoleProp.size());
    Assert.assertEquals("alluxio.metrics.sink.ConsoleSink", consoleProp.getProperty("class"));

    Properties jmxProp = sinkProps.get("jmx");
    Assert.assertEquals(1, jmxProp.size());
    Assert.assertEquals("alluxio.metrics.sink.JmxSink", jmxProp.getProperty("class"));
  }