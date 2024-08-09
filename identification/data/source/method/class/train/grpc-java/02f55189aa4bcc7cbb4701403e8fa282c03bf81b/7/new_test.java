  @Test
  public void getFallbackPolicyFromXdsConfig() throws Exception {
    String rawLbConfig = "{"
        + "\"childPolicy\" : [{\"round_robin\" : {}}, {\"lbPolicy2\" : {\"key\" : \"val\"}}],"
        + "\"fallbackPolicy\" : [{\"lbPolicy3\" : {\"key\" : \"val\"}}, {\"lbPolicy4\" : {}}]"
        + "}";
    LbConfig expectedFallbackPolicy1 = ServiceConfigUtil.unwrapLoadBalancingConfig(
        checkObject(JsonParser.parse("{\"lbPolicy3\" : {\"key\" : \"val\"}}")));
    LbConfig expectedFallbackPolicy2 = ServiceConfigUtil.unwrapLoadBalancingConfig(
        checkObject(JsonParser.parse("{\"lbPolicy4\" : {}}")));

    List<LbConfig> childPolicies = ServiceConfigUtil.getFallbackPolicyFromXdsConfig(
        checkObject(JsonParser.parse(rawLbConfig)));

    assertThat(childPolicies).containsExactly(expectedFallbackPolicy1, expectedFallbackPolicy2);
  }