@Test
    public void testSetItemId()
    {
        int itemId = 55;
        mv.setItemId(itemId);
        assertThat("testSetItemId 0", mv.getItemId(), equalTo(itemId));
    }