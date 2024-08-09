@Test
    public void testGetItems() throws Exception
    {
        Iterator<Item> items = itemService.findByCollection(context, collection);
        assertThat("testGetItems 0", items, notNullValue());
        //by default is empty
        assertFalse("testGetItems 1", items.hasNext());
    }