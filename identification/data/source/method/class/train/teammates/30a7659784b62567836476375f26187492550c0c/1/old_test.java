    @Test
    public void testToString() {
        List<String> strings = new ArrayList<>();
        assertEquals("", StringHelper.toString(strings, ""));
        assertEquals("", StringHelper.toString(strings, "<br>"));

        strings.add("aaa");
        assertEquals("aaa", StringHelper.toString(strings, ""));
        assertEquals("aaa", StringHelper.toString(strings, "\n"));
        assertEquals("aaa", StringHelper.toString(strings, "<br>"));

        strings.add("bbb");
        assertEquals("aaabbb", StringHelper.toString(strings, ""));
        assertEquals("aaa\nbbb", StringHelper.toString(strings, "\n"));
        assertEquals("aaa<br>bbb", StringHelper.toString(strings, "<br>"));

        List<Integer> ints = new ArrayList<>();
        ints.add(1);
        ints.add(44);
        assertEquals("1\n44", StringHelper.toString(ints, "\n"));
    }