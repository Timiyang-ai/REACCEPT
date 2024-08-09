@Test
  public void testBuildElementMap() throws Exception {
    final Document doc = XmlUtils.createDocumentFromReader(new InputStreamReader(this.getClass().getResourceAsStream("CacheElementJUnitTest.xml")));

    final LinkedHashMap<String, CacheElement> elementMap = CacheElement.buildElementMap(doc);

    final Iterator<Entry<String, CacheElement>> entries = elementMap.entrySet().iterator();

    int order = 0;
    assertEntry("cache-transaction-manager", order++, entries.next());
    assertEntry("dynamic-region-factory", order++, entries.next());
    assertEntry("gateway-hub", order++, entries.next());
    assertEntry("gateway-sender", order++, entries.next());
    assertEntry("gateway-receiver", order++, entries.next());
    assertEntry("gateway-conflict-resolver", order++, entries.next());
    assertEntry("async-event-queue", order++, entries.next());
    assertEntry("cache-server", order++, entries.next());
    assertEntry("bridge-server", order++, entries.next());
    assertEntry("pool", order++, entries.next());
    assertEntry("disk-store", order++, entries.next());
    assertEntry("pdx", order++, entries.next());
    assertEntry("region-attributes", order++, entries.next());
    assertEntry("jndi-bindings", order++, entries.next());
    assertEntry("region", order++, entries.next());
    assertEntry("vm-root-region", order++, entries.next());
    assertEntry("function-service", order++, entries.next());
    assertEntry("resource-manager", order++, entries.next());
    assertEntry("serialization-registration", order++, entries.next());
    assertEntry("backup", order++, entries.next());
    assertEntry("initializer", order++, entries.next());

    assertTrue("Extra entries in map.", !entries.hasNext());
  }