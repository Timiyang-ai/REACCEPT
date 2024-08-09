  @Test
  public void getChildPolicyFromXdsConfig() throws Exception {
    String rawLbConfig = "{"
        + "\"childPolicy\" : [{\"round_robin\" : {}}, {\"lbPolicy2\" : {\"key\" : \"val\"}}],"
        + "\"fallbackPolicy\" : [{\"lbPolicy3\" : {\"key\" : \"val\"}}, {\"lbPolicy4\" : {}}]"
        + "}";
    LbConfig expectedChildPolicy1 = ServiceConfigUtil.unwrapLoadBalancingConfig(
        checkObject(JsonParser.parse("{\"round_robin\" : {}}")));
    LbConfig expectedChildPolicy2 = ServiceConfigUtil.unwrapLoadBalancingConfig(
        checkObject(JsonParser.parse("{\"lbPolicy2\" : {\"key\" : \"val\"}}")));

    List<LbConfig> childPolicies = ServiceConfigUtil.getChildPolicyFromXdsConfig(
        checkObject(JsonParser.parse(rawLbConfig)));

    assertThat(childPolicies).containsExactly(expectedChildPolicy1, expectedChildPolicy2);
  }