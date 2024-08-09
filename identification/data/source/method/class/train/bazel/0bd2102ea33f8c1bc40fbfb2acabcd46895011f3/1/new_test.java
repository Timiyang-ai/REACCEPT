  @Test
  public void getRuleByName() throws Exception {
    if (!analysisMock.isThisBazel()) {
      return;
    }
    scratch.overwriteFile("WORKSPACE", "local_repository(name = 'foo', path = 'path/to/repo')");

    SkyKey key = getRuleByNameKey("foo");
    EvaluationResult<GetRuleByNameValue> result = getRuleByName(key);

    assertThatEvaluationResult(result).hasNoError();

    Rule rule = result.get(key).rule();
    assertThat(rule).isNotNull();
    assertThat(rule.getName()).isEqualTo("foo");
  }