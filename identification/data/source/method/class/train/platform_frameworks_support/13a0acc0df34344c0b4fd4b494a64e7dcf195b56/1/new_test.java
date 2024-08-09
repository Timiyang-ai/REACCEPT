    @Test
    public void popFromPreLayout() {
        assertEquals(0, sizeOf(FLAG_PRE));
        RecyclerView.ViewHolder vh = new MockViewHolder();
        MockInfo info = new MockInfo();
        mStore.addToPreLayout(vh, info);
        assertSame(info, mStore.popFromPreLayout(vh));
        assertNull(mStore.popFromPreLayout(vh));
    }