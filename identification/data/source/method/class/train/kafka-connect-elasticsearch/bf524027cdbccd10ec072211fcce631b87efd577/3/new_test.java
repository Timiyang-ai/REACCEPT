  @Test
  public void toBulkableAction(){
    JestElasticsearchClient client = new JestElasticsearchClient(jestClient);
    IndexableRecord del = new IndexableRecord(new Key("idx", "tp", "xxx"), null, 1L);
    BulkableAction ba = client.toBulkableAction(del);
    assertNotNull(ba);
    assertSame(Delete.class, ba.getClass());
    assertEquals(del.key.index, ba.getIndex());
    assertEquals(del.key.id, ba.getId());
    assertEquals(del.key.type, ba.getType());
    IndexableRecord idx = new IndexableRecord(new Key("idx", "tp", "xxx"), "yyy", 1L);
    ba = client.toBulkableAction(idx);
    assertNotNull(ba);
    assertSame(Index.class, ba.getClass());
    assertEquals(idx.key.index, ba.getIndex());
    assertEquals(idx.key.id, ba.getId());
    assertEquals(idx.key.type, ba.getType());
    assertEquals(idx.payload, ba.getData(null));
    // upsert
    client.setWriteMethod(JestElasticsearchClient.WriteMethod.UPSERT);
    ba = client.toBulkableAction(idx);
    assertNotNull(ba);
    assertSame(Update.class, ba.getClass());
    assertEquals(idx.key.index, ba.getIndex());
    assertEquals(idx.key.id, ba.getId());
    assertEquals(idx.key.type, ba.getType());
    assertEquals("{\"doc\":" + idx.payload + ", \"doc_as_upsert\":true}", ba.getData(null));
  }