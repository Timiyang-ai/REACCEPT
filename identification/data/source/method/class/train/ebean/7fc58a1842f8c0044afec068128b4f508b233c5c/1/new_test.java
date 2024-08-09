  @Test
  public void loadSettings() throws Exception {

    DocStoreConfig config = new DocStoreConfig();

    Properties properties = new Properties();
    properties.setProperty("ebean.docstore.active", "true");
    properties.setProperty("ebean.docstore.bulkBatchSize", "99");
    properties.setProperty("ebean.docstore.url", "http://foo:9800");
    properties.setProperty("ebean.docstore.persist", "IGNORE");
    properties.setProperty("ebean.docstore.allowAllCertificates", "true");
    properties.setProperty("ebean.docstore.username", "fred");
    properties.setProperty("ebean.docstore.password", "rock");

    PropertiesWrapper wrapper = new PropertiesWrapper("ebean", null, properties, null);

    config.loadSettings(wrapper);

    assertTrue(config.isActive());
    assertTrue(config.isAllowAllCertificates());
    assertFalse(config.isGenerateMapping());
    assertFalse(config.isDropCreate());
    assertEquals("http://foo:9800", config.getUrl());
    assertEquals("fred", config.getUsername());
    assertEquals("rock", config.getPassword());
    assertEquals(DocStoreMode.IGNORE, config.getPersist());
    assertEquals(99, config.getBulkBatchSize());
  }