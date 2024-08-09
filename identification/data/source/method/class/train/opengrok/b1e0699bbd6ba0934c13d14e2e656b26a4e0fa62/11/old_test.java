    @Test
    public void htmlize() throws IOException {
        String[][] input_output = {
            {"This is a test", "This is a test"},
            {"Newline\nshould become <br/>",
                "Newline<br/>should become &lt;br/&gt;"},
            {"Open & Grok", "Open &amp; Grok"},
            {"&amp;&lt;&gt;", "&amp;amp;&amp;lt;&amp;gt;"},};
        for (String[] in_out : input_output) {
            // 1 arg
            assertEquals(in_out[1], Util.htmlize(in_out[0]));
            // 2 args
            StringBuilder sb = new StringBuilder();
            Util.htmlize(in_out[0], sb);
            assertEquals(in_out[1], sb.toString());
        }
    }