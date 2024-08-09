@Test
    public void testSetItemId()
    {
        int itemId = 55;
        mv.setResourceId(itemId);
        mv.setResourceTypeId(Constants.ITEM);
        assertThat("testSetItemId 0", mv.getResourceId(), equalTo(itemId));
    }