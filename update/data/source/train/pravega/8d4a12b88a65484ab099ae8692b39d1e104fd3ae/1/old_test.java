@Test
    public void testGenerateInitialTableAttributes() {
        val updates = IndexWriter.generateInitialTableAttributes();
        val values = updates.stream().collect(Collectors.toMap(AttributeUpdate::getAttributeId, AttributeUpdate::getValue));
        Assert.assertEquals("Unexpected number of updates generated.", 1, values.size());
        Assert.assertEquals("Unexpected value for TableIndexOffset.", 0L, (long) values.get(Attributes.TABLE_INDEX_OFFSET));
    }