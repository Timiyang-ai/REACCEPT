@Test
    public void testGenerateInitialTableAttributes() {
        val updates = IndexWriter.generateInitialTableAttributes();
        val values = updates.stream().collect(Collectors.toMap(AttributeUpdate::getAttributeId, AttributeUpdate::getValue));
        Assert.assertEquals("Unexpected number of updates generated.", 3, values.size());
        Assert.assertEquals("Unexpected value for TableIndexOffset.", 0L, (long) values.get(Attributes.TABLE_INDEX_OFFSET));
        Assert.assertEquals("Unexpected value for TableEntryCount.", 0L, (long) values.get(Attributes.TABLE_ENTRY_COUNT));
        Assert.assertEquals("Unexpected value for TableBucketCount.", 0L, (long) values.get(Attributes.TABLE_BUCKET_COUNT));
    }