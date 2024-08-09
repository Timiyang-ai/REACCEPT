    @Test
    public void test_isEmptyColumn() {
        MarkdownTable table6 = getTable(markdown6);
        for (int i = 0; i < 10; i++) {
            assertEquals("Column: " + i, i == 0 || i > 4, table6.isEmptyColumn(i));
        }
    }