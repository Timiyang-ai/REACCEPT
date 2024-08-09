    @Test
    public void test_setCaptionWithMarkers() {
        MarkdownTable table1 = getTable(markdown1);
        MarkdownTable table2 = getTable(markdown2);
        MarkdownTable table3 = getTable(markdown3);

        table1.setCaptionWithMarkers(null, "[", "Table 1", "]");
        table2.setCaptionWithMarkers(null, "[", "Table 2", "]");
        table3.setCaptionWithMarkers(null, "[", "Table 3", "]");

        assertEquals("Table 1", table1.getCaption().toString());
        assertEquals("Table 2", table2.getCaption().toString());
        assertEquals("Table 3", table3.getCaption().toString());

        assertEquals(formattedNoCaption1 + "[Table 1]\n", getFormattedTable(table1).toString());
        assertEquals(formattedNoCaption2 + "[Table 2]\n", getFormattedTable(table2).toString());
        assertEquals(formattedNoCaption3 + "[Table 3]\n", getFormattedTable(table3).toString());
    }