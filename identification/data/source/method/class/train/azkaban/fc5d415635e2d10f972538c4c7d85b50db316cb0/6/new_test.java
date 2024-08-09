@Test
  public void testLoadValidators() {
    final Props props = new Props(this.baseProps);
    final URL validatorUrl = Resources.getResource("project/testValidators");
    final URL configUrl = Resources.getResource("test-conf/azkaban-validators-test2.xml");
    props.put(ValidatorConfigs.VALIDATOR_PLUGIN_DIR, validatorUrl.getPath());
    props.put(ValidatorConfigs.XML_FILE_PARAM, configUrl.getPath());

    final XmlValidatorManager manager = new XmlValidatorManager(props);
    assertEquals("XmlValidatorManager should contain 2 validators.",
        manager.getValidatorsInfo().size(), 2);
    assertEquals(
        "XmlValidatorManager should contain the validator specified in the xml configuration file.",
        manager.getValidatorsInfo().get(1), "Test");
  }