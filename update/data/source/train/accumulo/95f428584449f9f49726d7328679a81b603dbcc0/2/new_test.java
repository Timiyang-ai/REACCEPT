@Test
  public void testSplit3() throws Exception {
    MockInstance mi = new MockInstance();
    Connector conn = mi.getConnector("", new PasswordToken(""));

    BatchWriter bw1 = conn.createBatchWriter(MetadataTable.NAME, new BatchWriterConfig());

    bw1.addMutation(createTablet("0", "m", null, "/d1", "/d1/file1"));
    bw1.addMutation(createTablet("0", null, "m", "/d2", "/d2/file2"));

    bw1.flush();

    BatchWriter bw2 = conn.createBatchWriter(MetadataTable.NAME, new BatchWriterConfig());

    MetadataTableUtil.initializeClone("0", "1", conn, bw2);

    bw1.addMutation(createTablet("0", "f", null, "/d1", "/d1/file3"));
    bw1.addMutation(createTablet("0", "m", "f", "/d3", "/d1/file1"));
    bw1.addMutation(createTablet("0", "s", "m", "/d2", "/d2/file2"));
    bw1.addMutation(createTablet("0", null, "s", "/d4", "/d2/file2"));

    bw1.flush();

    int rc = MetadataTableUtil.checkClone("0", "1", conn, bw2);

    assertEquals(0, rc);

    Scanner scanner = conn.createScanner(MetadataTable.NAME, Authorizations.EMPTY);
    scanner.setRange(new KeyExtent(new Text("1"), null, null).toMetadataRange());

    HashSet<String> files = new HashSet<>();

    int count = 0;
    for (Entry<Key,Value> entry : scanner) {
      if (entry.getKey().getColumnFamily().equals(DataFileColumnFamily.NAME)) {
        files.add(entry.getKey().getColumnQualifier().toString());
        count++;
      }
    }

    assertEquals(2, count);
    assertEquals(2, files.size());
    assertTrue(files.contains("../0/d1/file1"));
    assertTrue(files.contains("../0/d2/file2"));
  }