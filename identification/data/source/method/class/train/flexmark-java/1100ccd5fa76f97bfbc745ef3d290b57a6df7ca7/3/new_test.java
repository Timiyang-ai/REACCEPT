    @Test
    public void test_previousOffsetStop() {
        String markdown = "" +
                "| Features                                                                        | Basic | Enhanced |    |\n" +
                "|:--------------------------------------------------------------------------------|:-----:|:--------:|:---|\n" +
                "| Works with builds 143.2730 or newer, product version IDEA 14.1.4                |   X   |    X     |    |\n" +
                "| Preview Tab so you can see what the rendered markdown will look like on GitHub. |   X   |    X     |    |\n" +
                "| Syntax highlighting                                                             |   X   |    X     |    |\n" +
                "[ testing ]\n^" +
                "";

        int pos = markdown.indexOf("^");
        CharSequence charSequence = markdown.substring(0, pos) + markdown.substring(pos + 1);
        BasedSequence source = BasedSequence.of(charSequence);
        MarkdownTable table = getTable(source, formatOptions("", null));
        HtmlWriter out = new HtmlWriter(0, HtmlWriter.F_FORMAT_ALL);
        table.appendTable(out);
        String formattedTable = out.toString(0);
        TableCellOffsetInfo offsetInfo = table.getCellOffsetInfo(pos);

        assertEquals("" +
                "| Features                                                                        | Basic | Enhanced |    |\n" +
                "|:--------------------------------------------------------------------------------|:-----:|:--------:|:---|\n" +
                "| Works with builds 143.2730 or newer, product version IDEA 14.1.4                |   X   |    X     |    |\n" +
                "| Preview Tab so you can see what the rendered markdown will look like on GitHub. |   X   |    X     |    |\n" +
                "| Syntax highlighting                                                             |   X   |    X     |    |\n" +
                "[ testing ]\n^" +
                "", formattedTable.substring(0, offsetInfo.offset) + "^" + formattedTable.substring(offsetInfo.offset));

        offsetInfo = offsetInfo.previousOffsetStop(null);
        assertEquals("" +
                "| Features                                                                        | Basic | Enhanced |    |\n" +
                "|:--------------------------------------------------------------------------------|:-----:|:--------:|:---|\n" +
                "| Works with builds 143.2730 or newer, product version IDEA 14.1.4                |   X   |    X     |    |\n" +
                "| Preview Tab so you can see what the rendered markdown will look like on GitHub. |   X   |    X     |    |\n" +
                "| Syntax highlighting                                                             |   X   |    X     |    |\n" +
                "[ testing^ ]\n" +
                "", formattedTable.substring(0, offsetInfo.offset) + "^" + formattedTable.substring(offsetInfo.offset));

        offsetInfo = offsetInfo.previousOffsetStop(null);
        assertEquals("" +
                "| Features                                                                        | Basic | Enhanced |    |\n" +
                "|:--------------------------------------------------------------------------------|:-----:|:--------:|:---|\n" +
                "| Works with builds 143.2730 or newer, product version IDEA 14.1.4                |   X   |    X     |    |\n" +
                "| Preview Tab so you can see what the rendered markdown will look like on GitHub. |   X   |    X     |    |\n" +
                "| Syntax highlighting                                                             |   X   |    X     | ^   |\n" +
                "[ testing ]\n" +
                "", formattedTable.substring(0, offsetInfo.offset) + "^" + formattedTable.substring(offsetInfo.offset));

        offsetInfo = offsetInfo.previousOffsetStop(null);
        assertEquals("" +
                "| Features                                                                        | Basic | Enhanced |    |\n" +
                "|:--------------------------------------------------------------------------------|:-----:|:--------:|:---|\n" +
                "| Works with builds 143.2730 or newer, product version IDEA 14.1.4                |   X   |    X     |    |\n" +
                "| Preview Tab so you can see what the rendered markdown will look like on GitHub. |   X   |    X     |    |\n" +
                "| Syntax highlighting                                                             |   X   |    X^     |    |\n" +
                "[ testing ]\n" +
                "", formattedTable.substring(0, offsetInfo.offset) + "^" + formattedTable.substring(offsetInfo.offset));

        offsetInfo = offsetInfo.previousOffsetStop(null);
        assertEquals("" +
                "| Features                                                                        | Basic | Enhanced |    |\n" +
                "|:--------------------------------------------------------------------------------|:-----:|:--------:|:---|\n" +
                "| Works with builds 143.2730 or newer, product version IDEA 14.1.4                |   X   |    X     |    |\n" +
                "| Preview Tab so you can see what the rendered markdown will look like on GitHub. |   X   |    X     |    |\n" +
                "| Syntax highlighting                                                             |   X^   |    X     |    |\n" +
                "[ testing ]\n" +
                "", formattedTable.substring(0, offsetInfo.offset) + "^" + formattedTable.substring(offsetInfo.offset));

        offsetInfo = offsetInfo.previousOffsetStop(null);
        assertEquals("" +
                "| Features                                                                        | Basic | Enhanced |    |\n" +
                "|:--------------------------------------------------------------------------------|:-----:|:--------:|:---|\n" +
                "| Works with builds 143.2730 or newer, product version IDEA 14.1.4                |   X   |    X     |    |\n" +
                "| Preview Tab so you can see what the rendered markdown will look like on GitHub. |   X   |    X     |    |\n" +
                "| Syntax highlighting^                                                             |   X   |    X     |    |\n" +
                "[ testing ]\n" +
                "", formattedTable.substring(0, offsetInfo.offset) + "^" + formattedTable.substring(offsetInfo.offset));

        offsetInfo = offsetInfo.previousOffsetStop(null);
        assertEquals("" +
                "| Features                                                                        | Basic | Enhanced |    |\n" +
                "|:--------------------------------------------------------------------------------|:-----:|:--------:|:---|\n" +
                "| Works with builds 143.2730 or newer, product version IDEA 14.1.4                |   X   |    X     |    |\n" +
                "| Preview Tab so you can see what the rendered markdown will look like on GitHub. |   X   |    X     | ^   |\n" +
                "| Syntax highlighting                                                             |   X   |    X     |    |\n" +
                "[ testing ]\n" +
                "", formattedTable.substring(0, offsetInfo.offset) + "^" + formattedTable.substring(offsetInfo.offset));

        offsetInfo = offsetInfo.previousOffsetStop(null);
        assertEquals("" +
                "| Features                                                                        | Basic | Enhanced |    |\n" +
                "|:--------------------------------------------------------------------------------|:-----:|:--------:|:---|\n" +
                "| Works with builds 143.2730 or newer, product version IDEA 14.1.4                |   X   |    X     |    |\n" +
                "| Preview Tab so you can see what the rendered markdown will look like on GitHub. |   X   |    X^     |    |\n" +
                "| Syntax highlighting                                                             |   X   |    X     |    |\n" +
                "[ testing ]\n" +
                "", formattedTable.substring(0, offsetInfo.offset) + "^" + formattedTable.substring(offsetInfo.offset));

        offsetInfo = offsetInfo.previousOffsetStop(null);
        assertEquals("" +
                "| Features                                                                        | Basic | Enhanced |    |\n" +
                "|:--------------------------------------------------------------------------------|:-----:|:--------:|:---|\n" +
                "| Works with builds 143.2730 or newer, product version IDEA 14.1.4                |   X   |    X     |    |\n" +
                "| Preview Tab so you can see what the rendered markdown will look like on GitHub. |   X^   |    X     |    |\n" +
                "| Syntax highlighting                                                             |   X   |    X     |    |\n" +
                "[ testing ]\n" +
                "", formattedTable.substring(0, offsetInfo.offset) + "^" + formattedTable.substring(offsetInfo.offset));

        offsetInfo = offsetInfo.previousOffsetStop(null);
        assertEquals("" +
                "| Features                                                                        | Basic | Enhanced |    |\n" +
                "|:--------------------------------------------------------------------------------|:-----:|:--------:|:---|\n" +
                "| Works with builds 143.2730 or newer, product version IDEA 14.1.4                |   X   |    X     |    |\n" +
                "| Preview Tab so you can see what the rendered markdown will look like on GitHub.^ |   X   |    X     |    |\n" +
                "| Syntax highlighting                                                             |   X   |    X     |    |\n" +
                "[ testing ]\n" +
                "", formattedTable.substring(0, offsetInfo.offset) + "^" + formattedTable.substring(offsetInfo.offset));

        offsetInfo = offsetInfo.previousOffsetStop(null);
        assertEquals("" +
                "| Features                                                                        | Basic | Enhanced |    |\n" +
                "|:--------------------------------------------------------------------------------|:-----:|:--------:|:---|\n" +
                "| Works with builds 143.2730 or newer, product version IDEA 14.1.4                |   X   |    X     | ^   |\n" +
                "| Preview Tab so you can see what the rendered markdown will look like on GitHub. |   X   |    X     |    |\n" +
                "| Syntax highlighting                                                             |   X   |    X     |    |\n" +
                "[ testing ]\n" +
                "", formattedTable.substring(0, offsetInfo.offset) + "^" + formattedTable.substring(offsetInfo.offset));

        offsetInfo = offsetInfo.previousOffsetStop(null);
        assertEquals("" +
                "| Features                                                                        | Basic | Enhanced |    |\n" +
                "|:--------------------------------------------------------------------------------|:-----:|:--------:|:---|\n" +
                "| Works with builds 143.2730 or newer, product version IDEA 14.1.4                |   X   |    X^     |    |\n" +
                "| Preview Tab so you can see what the rendered markdown will look like on GitHub. |   X   |    X     |    |\n" +
                "| Syntax highlighting                                                             |   X   |    X     |    |\n" +
                "[ testing ]\n" +
                "", formattedTable.substring(0, offsetInfo.offset) + "^" + formattedTable.substring(offsetInfo.offset));

        offsetInfo = offsetInfo.previousOffsetStop(null);
        assertEquals("" +
                "| Features                                                                        | Basic | Enhanced |    |\n" +
                "|:--------------------------------------------------------------------------------|:-----:|:--------:|:---|\n" +
                "| Works with builds 143.2730 or newer, product version IDEA 14.1.4                |   X^   |    X     |    |\n" +
                "| Preview Tab so you can see what the rendered markdown will look like on GitHub. |   X   |    X     |    |\n" +
                "| Syntax highlighting                                                             |   X   |    X     |    |\n" +
                "[ testing ]\n" +
                "", formattedTable.substring(0, offsetInfo.offset) + "^" + formattedTable.substring(offsetInfo.offset));

        offsetInfo = offsetInfo.previousOffsetStop(null);
        assertEquals("" +
                "| Features                                                                        | Basic | Enhanced |    |\n" +
                "|:--------------------------------------------------------------------------------|:-----:|:--------:|:---|\n" +
                "| Works with builds 143.2730 or newer, product version IDEA 14.1.4^                |   X   |    X     |    |\n" +
                "| Preview Tab so you can see what the rendered markdown will look like on GitHub. |   X   |    X     |    |\n" +
                "| Syntax highlighting                                                             |   X   |    X     |    |\n" +
                "[ testing ]\n" +
                "", formattedTable.substring(0, offsetInfo.offset) + "^" + formattedTable.substring(offsetInfo.offset));

        offsetInfo = offsetInfo.previousOffsetStop(null);
        assertEquals("" +
                "| Features                                                                        | Basic | Enhanced |    |\n" +
                "|:--------------------------------------------------------------------------------|:-----:|:--------:|:---^|\n" +
                "| Works with builds 143.2730 or newer, product version IDEA 14.1.4                |   X   |    X     |    |\n" +
                "| Preview Tab so you can see what the rendered markdown will look like on GitHub. |   X   |    X     |    |\n" +
                "| Syntax highlighting                                                             |   X   |    X     |    |\n" +
                "[ testing ]\n" +
                "", formattedTable.substring(0, offsetInfo.offset) + "^" + formattedTable.substring(offsetInfo.offset));

        offsetInfo = offsetInfo.previousOffsetStop(null);
        assertEquals("" +
                "| Features                                                                        | Basic | Enhanced |    |\n" +
                "|:--------------------------------------------------------------------------------|:-----:|:--------:|^:---|\n" +
                "| Works with builds 143.2730 or newer, product version IDEA 14.1.4                |   X   |    X     |    |\n" +
                "| Preview Tab so you can see what the rendered markdown will look like on GitHub. |   X   |    X     |    |\n" +
                "| Syntax highlighting                                                             |   X   |    X     |    |\n" +
                "[ testing ]\n" +
                "", formattedTable.substring(0, offsetInfo.offset) + "^" + formattedTable.substring(offsetInfo.offset));

        offsetInfo = offsetInfo.previousOffsetStop(null);
        assertEquals("" +
                "| Features                                                                        | Basic | Enhanced |    |\n" +
                "|:--------------------------------------------------------------------------------|:-----:|:--------:^|:---|\n" +
                "| Works with builds 143.2730 or newer, product version IDEA 14.1.4                |   X   |    X     |    |\n" +
                "| Preview Tab so you can see what the rendered markdown will look like on GitHub. |   X   |    X     |    |\n" +
                "| Syntax highlighting                                                             |   X   |    X     |    |\n" +
                "[ testing ]\n" +
                "", formattedTable.substring(0, offsetInfo.offset) + "^" + formattedTable.substring(offsetInfo.offset));

        offsetInfo = offsetInfo.previousOffsetStop(null);
        assertEquals("" +
                "| Features                                                                        | Basic | Enhanced |    |\n" +
                "|:--------------------------------------------------------------------------------|:-----:|^:--------:|:---|\n" +
                "| Works with builds 143.2730 or newer, product version IDEA 14.1.4                |   X   |    X     |    |\n" +
                "| Preview Tab so you can see what the rendered markdown will look like on GitHub. |   X   |    X     |    |\n" +
                "| Syntax highlighting                                                             |   X   |    X     |    |\n" +
                "[ testing ]\n" +
                "", formattedTable.substring(0, offsetInfo.offset) + "^" + formattedTable.substring(offsetInfo.offset));

        offsetInfo = offsetInfo.previousOffsetStop(null);
        assertEquals("" +
                "| Features                                                                        | Basic | Enhanced |    |\n" +
                "|:--------------------------------------------------------------------------------|:-----:^|:--------:|:---|\n" +
                "| Works with builds 143.2730 or newer, product version IDEA 14.1.4                |   X   |    X     |    |\n" +
                "| Preview Tab so you can see what the rendered markdown will look like on GitHub. |   X   |    X     |    |\n" +
                "| Syntax highlighting                                                             |   X   |    X     |    |\n" +
                "[ testing ]\n" +
                "", formattedTable.substring(0, offsetInfo.offset) + "^" + formattedTable.substring(offsetInfo.offset));

        offsetInfo = offsetInfo.previousOffsetStop(null);
        assertEquals("" +
                "| Features                                                                        | Basic | Enhanced |    |\n" +
                "|:--------------------------------------------------------------------------------|^:-----:|:--------:|:---|\n" +
                "| Works with builds 143.2730 or newer, product version IDEA 14.1.4                |   X   |    X     |    |\n" +
                "| Preview Tab so you can see what the rendered markdown will look like on GitHub. |   X   |    X     |    |\n" +
                "| Syntax highlighting                                                             |   X   |    X     |    |\n" +
                "[ testing ]\n" +
                "", formattedTable.substring(0, offsetInfo.offset) + "^" + formattedTable.substring(offsetInfo.offset));

        offsetInfo = offsetInfo.previousOffsetStop(null);
        assertEquals("" +
                "| Features                                                                        | Basic | Enhanced |    |\n" +
                "|:--------------------------------------------------------------------------------^|:-----:|:--------:|:---|\n" +
                "| Works with builds 143.2730 or newer, product version IDEA 14.1.4                |   X   |    X     |    |\n" +
                "| Preview Tab so you can see what the rendered markdown will look like on GitHub. |   X   |    X     |    |\n" +
                "| Syntax highlighting                                                             |   X   |    X     |    |\n" +
                "[ testing ]\n" +
                "", formattedTable.substring(0, offsetInfo.offset) + "^" + formattedTable.substring(offsetInfo.offset));

        offsetInfo = offsetInfo.previousOffsetStop(null);
        assertEquals("" +
                "| Features                                                                        | Basic | Enhanced |    |\n" +
                "|^:--------------------------------------------------------------------------------|:-----:|:--------:|:---|\n" +
                "| Works with builds 143.2730 or newer, product version IDEA 14.1.4                |   X   |    X     |    |\n" +
                "| Preview Tab so you can see what the rendered markdown will look like on GitHub. |   X   |    X     |    |\n" +
                "| Syntax highlighting                                                             |   X   |    X     |    |\n" +
                "[ testing ]\n" +
                "", formattedTable.substring(0, offsetInfo.offset) + "^" + formattedTable.substring(offsetInfo.offset));

        offsetInfo = offsetInfo.previousOffsetStop(null);
        assertEquals("" +
                "| Features                                                                        | Basic | Enhanced | ^   |\n" +
                "|:--------------------------------------------------------------------------------|:-----:|:--------:|:---|\n" +
                "| Works with builds 143.2730 or newer, product version IDEA 14.1.4                |   X   |    X     |    |\n" +
                "| Preview Tab so you can see what the rendered markdown will look like on GitHub. |   X   |    X     |    |\n" +
                "| Syntax highlighting                                                             |   X   |    X     |    |\n" +
                "[ testing ]\n" +
                "", formattedTable.substring(0, offsetInfo.offset) + "^" + formattedTable.substring(offsetInfo.offset));

        offsetInfo = offsetInfo.previousOffsetStop(null);
        assertEquals("" +
                "| Features                                                                        | Basic | Enhanced^ |    |\n" +
                "|:--------------------------------------------------------------------------------|:-----:|:--------:|:---|\n" +
                "| Works with builds 143.2730 or newer, product version IDEA 14.1.4                |   X   |    X     |    |\n" +
                "| Preview Tab so you can see what the rendered markdown will look like on GitHub. |   X   |    X     |    |\n" +
                "| Syntax highlighting                                                             |   X   |    X     |    |\n" +
                "[ testing ]\n" +
                "", formattedTable.substring(0, offsetInfo.offset) + "^" + formattedTable.substring(offsetInfo.offset));

        offsetInfo = offsetInfo.previousOffsetStop(null);
        assertEquals("" +
                "| Features                                                                        | Basic^ | Enhanced |    |\n" +
                "|:--------------------------------------------------------------------------------|:-----:|:--------:|:---|\n" +
                "| Works with builds 143.2730 or newer, product version IDEA 14.1.4                |   X   |    X     |    |\n" +
                "| Preview Tab so you can see what the rendered markdown will look like on GitHub. |   X   |    X     |    |\n" +
                "| Syntax highlighting                                                             |   X   |    X     |    |\n" +
                "[ testing ]\n" +
                "", formattedTable.substring(0, offsetInfo.offset) + "^" + formattedTable.substring(offsetInfo.offset));

        offsetInfo = offsetInfo.previousOffsetStop(null);
        assertEquals("" +
                "| Features^                                                                        | Basic | Enhanced |    |\n" +
                "|:--------------------------------------------------------------------------------|:-----:|:--------:|:---|\n" +
                "| Works with builds 143.2730 or newer, product version IDEA 14.1.4                |   X   |    X     |    |\n" +
                "| Preview Tab so you can see what the rendered markdown will look like on GitHub. |   X   |    X     |    |\n" +
                "| Syntax highlighting                                                             |   X   |    X     |    |\n" +
                "[ testing ]\n" +
                "", formattedTable.substring(0, offsetInfo.offset) + "^" + formattedTable.substring(offsetInfo.offset));

        offsetInfo = offsetInfo.previousOffsetStop(null);
        assertEquals("" +
                "^| Features                                                                        | Basic | Enhanced |    |\n" +
                "|:--------------------------------------------------------------------------------|:-----:|:--------:|:---|\n" +
                "| Works with builds 143.2730 or newer, product version IDEA 14.1.4                |   X   |    X     |    |\n" +
                "| Preview Tab so you can see what the rendered markdown will look like on GitHub. |   X   |    X     |    |\n" +
                "| Syntax highlighting                                                             |   X   |    X     |    |\n" +
                "[ testing ]\n" +
                "", formattedTable.substring(0, offsetInfo.offset) + "^" + formattedTable.substring(offsetInfo.offset));
    }