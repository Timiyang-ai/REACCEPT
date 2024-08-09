    @Test
    public void clearTest() {
        insert(new Item(1));
        insert(new Item(2));
        assertEquals(2, mList.size());
        mList.clear();
        assertEquals(0, mList.size());
        insert(new Item(3));
        assertEquals(1, mList.size());
    }