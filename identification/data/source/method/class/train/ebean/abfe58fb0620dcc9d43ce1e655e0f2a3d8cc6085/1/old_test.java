  @Test
  public void byName() throws Exception {

    assertEquals(lookup.byName("DECIMAL"), DbType.DECIMAL);
    assertEquals(lookup.byName("Decimal"), DbType.DECIMAL);
    assertEquals(lookup.byName("decimal"), DbType.DECIMAL);

    assertEquals(lookup.byName("varchar"), DbType.VARCHAR);
    assertEquals(lookup.byName("varchar2"), DbType.VARCHAR);

    assertEquals(lookup.byName("float"), DbType.REAL);
    assertEquals(lookup.byName("real"), DbType.REAL);

    assertEquals(lookup.byName("uuid"), DbType.UUID);
    assertEquals(lookup.byName("hstore"), DbType.HSTORE);

    assertEquals(lookup.byName("json"), DbType.JSON);
    assertEquals(lookup.byName("jsonb"), DbType.JSONB);
    assertEquals(lookup.byName("jsonclob"), DbType.JSONCLOB);
    assertEquals(lookup.byName("jsonblob"), DbType.JSONBLOB);
    assertEquals(lookup.byName("jsonVarchar"), DbType.JSONVARCHAR);

  }