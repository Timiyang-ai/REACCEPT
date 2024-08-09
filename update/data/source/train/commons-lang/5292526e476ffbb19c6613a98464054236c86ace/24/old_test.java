@Test
    public void testAppendAll_Iterator() {
        StrBuilder sb = new StrBuilder();
        sb.appendAll((Iterator<?>) null);
        assertEquals("", sb.toString());

        sb.clear();
        sb.appendAll(Collections.EMPTY_LIST.iterator());
        assertEquals("", sb.toString());

        sb.clear();
        sb.appendAll(Arrays.asList(new Object[]{"foo", "bar", "baz"}).iterator());
        assertEquals("foobarbaz", sb.toString());
    }