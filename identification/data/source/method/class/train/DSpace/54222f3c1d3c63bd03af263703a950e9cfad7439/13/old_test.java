@Test
    public void testGetItems() throws Exception
    {
        ItemIterator items = c.getItems();
        assertThat("testGetItems 0", items, notNullValue());
        //by default is empty
        assertFalse("testGetItems 1", items.hasNext());
        assertThat("testGetItems 2", items.next(), nullValue());
        assertThat("testGetItems 3", items.nextID(), equalTo(-1));
    }