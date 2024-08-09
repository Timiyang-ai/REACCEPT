@Test
  public void testDelete() throws MetaDataException {
    // add an entry with a text and binary field
    MetaDataEntry meta = new MetaDataEntry("tbd", "whatever");
    meta.addField("text", "some text");
    meta.addField("binary", new byte[] { 'b', 'i', 'n' });
    mds.add(meta);

    // verify it was written
    Assert.assertEquals(meta, mds.get("tbd", "whatever"));

    // delete it
    mds.delete("tbd", "whatever");

    // verify it's gone
    Assert.assertNull(mds.get("tbd", "whatever"));
    // TODO can't list until every implementation supports it
    // Assert.assertFalse(mds.list(null, null).contains(meta));

    // add another entry with same name and type
    MetaDataEntry meta1 = new MetaDataEntry("tbd", "whatever");
    meta1.addField("other", "other text");
    // add should succeed, update should fail
    try {
      mds.update(meta1);
      Assert.fail("update should fail");
    } catch (MetaDataException e) {
      //expected
    }
    mds.add(meta1);

    // read back entry and verify that it does not contain spurious
    // fields from the old meta data entry
    // verify it was written
    Assert.assertEquals(meta1, mds.get("tbd", "whatever"));
  }