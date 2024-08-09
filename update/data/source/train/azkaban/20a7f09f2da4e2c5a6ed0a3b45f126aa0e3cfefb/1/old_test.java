@Test
  public void testLoadValidators() {
    Props props = new Props(baseProps);
    URL validatorUrl = Resources.getResource("project/testValidators");
    URL configUrl = Resources.getResource("test-conf/azkaban-validators-test2.xml");
    props.put(XmlValidatorManager.VALIDATOR_PLUGIN_DIR, validatorUrl.getPath());
    props.put(XmlValidatorManager.XML_FILE_PARAM,
        configUrl.getPath());

    XmlValidatorManager manager = new XmlValidatorManager(props);
    assertEquals("XmlValidatorManager should contain 2 validators.", manager.getValidatorsInfo().size(), 2);
    assertEquals("XmlValidatorManager should contain the validator specified in the xml configuration file.",
        manager.getValidatorsInfo().get(1), "Test");
  }