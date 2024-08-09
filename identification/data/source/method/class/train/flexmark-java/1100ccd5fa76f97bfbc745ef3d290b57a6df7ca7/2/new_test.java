    @Test
    public void test_nextOffsetStop1() {
        String markdown = "" +
                "|            Default             | Lefts                          |    Right | Centered in a column^ |   |\n" +
                "|            Default             | Lefts                          |    Right |        Center        |   |\n" +
                "|--------------------------------|:-------------------------------|---------:|:--------------------:|---|\n" +
                "| item 1                         | item 1                         |   125.30 |          a           |   |\n" +
                "| item 2                         | item 2                         | 1,234.00 |          bc          |   |\n" +
                "| item 3 much longer description | item 3 much longer description |    10.50 |         def          |   |\n" +
                "| item 4 short                   | item 4 short                   |    34.10 |          h           |   |\n" +
                "[ cap**tion** ]\n" +
                "";

        int pos = markdown.indexOf("^");
        CharSequence charSequence = markdown.substring(0, pos) + markdown.substring(pos + 1);
        BasedSequence source = BasedSequence.of(charSequence);
        MarkdownTable table = getTable(source, formatOptions("", null).toMutable().set(TableFormatOptions.FORMAT_TABLE_LEFT_ALIGN_MARKER, DiscretionaryText.AS_IS));
        HtmlWriter out = new HtmlWriter(0, HtmlWriter.F_FORMAT_ALL);
        table.appendTable(out);
        String formattedTable = out.toString(0);
        TableCellOffsetInfo offsetInfo = table.getCellOffsetInfo(pos);

        assertEquals("" +
                "|            Default             | Lefts                          |    Right | Centered in a column^ |   |\n" +
                "|            Default             | Lefts                          |    Right |        Center        |   |\n" +
                "|--------------------------------|:-------------------------------|---------:|:--------------------:|---|\n" +
                "| item 1                         | item 1                         |   125.30 |          a           |   |\n" +
                "| item 2                         | item 2                         | 1,234.00 |          bc          |   |\n" +
                "| item 3 much longer description | item 3 much longer description |    10.50 |         def          |   |\n" +
                "| item 4 short                   | item 4 short                   |    34.10 |          h           |   |\n" +
                "[ cap**tion** ]\n" +
                "", formattedTable.substring(0, offsetInfo.offset) + "^" + formattedTable.substring(offsetInfo.offset));

        offsetInfo = offsetInfo.nextOffsetStop(null);
        assertEquals("" +
                "|            Default             | Lefts                          |    Right | Centered in a column | ^  |\n" +
                "|            Default             | Lefts                          |    Right |        Center        |   |\n" +
                "|--------------------------------|:-------------------------------|---------:|:--------------------:|---|\n" +
                "| item 1                         | item 1                         |   125.30 |          a           |   |\n" +
                "| item 2                         | item 2                         | 1,234.00 |          bc          |   |\n" +
                "| item 3 much longer description | item 3 much longer description |    10.50 |         def          |   |\n" +
                "| item 4 short                   | item 4 short                   |    34.10 |          h           |   |\n" +
                "[ cap**tion** ]\n" +
                "", formattedTable.substring(0, offsetInfo.offset) + "^" + formattedTable.substring(offsetInfo.offset));
    }