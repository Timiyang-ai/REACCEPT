  @Test
  public void unwrapLoadBalancingConfig() throws Exception {
    String lbConfig = "{\"xds_experimental\" : { "
        + "\"childPolicy\" : [{\"round_robin\" : {}}, {\"lbPolicy2\" : {\"key\" : \"val\"}}]"
        + "}}";

    LbConfig config =
        ServiceConfigUtil.unwrapLoadBalancingConfig(checkObject(JsonParser.parse(lbConfig)));
    assertThat(config.getPolicyName()).isEqualTo("xds_experimental");
    assertThat(config.getRawConfigValue()).isEqualTo(JsonParser.parse(
            "{\"childPolicy\" : [{\"round_robin\" : {}}, {\"lbPolicy2\" : {\"key\" : \"val\"}}]"
            + "}"));
  }