@Test
    public void testParseQuery11() {
        String raw = "  create tAble xxx   (col1_name type(), col2_name type());";
        ID = 11;
        String expected = raw.replaceAll("\\s*;+\\s*", ";").trim();
        assertThis(raw, expected, 1, ID);
    }