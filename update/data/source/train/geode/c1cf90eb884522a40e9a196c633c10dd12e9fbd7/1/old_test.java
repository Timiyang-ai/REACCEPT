@Test
  public void testCreateAndUpgradeDocumentFromXml() throws Exception {
    Document doc = SharedConfiguration.createAndUpgradeDocumentFromXml(IOUtils.toString(this.getClass().getResourceAsStream("SharedConfigurationJUnitTest.xml")));

    String schemaLocation = XmlUtils.getAttribute(doc.getDocumentElement(), W3C_XML_SCHEMA_INSTANCE_ATTRIBUTE_SCHEMA_LOCATION, W3C_XML_SCHEMA_INSTANCE_NS_URI);

    assertNotNull(schemaLocation);
    assertEquals(CacheXml.NAMESPACE + " " + CacheXml.LATEST_SCHEMA_LOCATION, schemaLocation);

    assertEquals(CacheXml.VERSION_LATEST, XmlUtils.getAttribute(doc.getDocumentElement(), "version"));
  }