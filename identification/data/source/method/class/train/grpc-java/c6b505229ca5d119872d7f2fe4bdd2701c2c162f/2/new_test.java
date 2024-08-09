  @Test
  public void unwrapLoadBalancingConfigList() throws Exception {
    String lbConfig = "[ "
        + "{\"xds_experimental\" : {\"unknown_field\" : \"dns:///balancer.example.com:8080\"} },"
        + "{\"grpclb\" : {} } ]";
    List<LbConfig> configs =
        ServiceConfigUtil.unwrapLoadBalancingConfigList(
            checkObjectList(JsonParser.parse(lbConfig)));
    assertThat(configs).containsExactly(
        ServiceConfigUtil.unwrapLoadBalancingConfig(checkObject(JsonParser.parse(
                "{\"xds_experimental\" : "
                + "{\"unknown_field\" : \"dns:///balancer.example.com:8080\"} }"))),
        ServiceConfigUtil.unwrapLoadBalancingConfig(checkObject(JsonParser.parse(
                "{\"grpclb\" : {} }")))).inOrder();
  }