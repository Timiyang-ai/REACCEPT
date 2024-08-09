    @Test
    public void convertHeaders() {
        Map<String, List<String>> headers = new HashMap<>();
        headers.put(null, Collections.singletonList("Ignored"));
        headers.put("HeaderA", Collections.singletonList("ValueA"));
        List<String> values = new ArrayList<>();
        values.add("ValueB_1");
        values.add("ValueB_2");
        headers.put("HeaderB", values);
        List<Header> result = HurlStack.convertHeaders(headers);
        List<Header> expected = new ArrayList<>();
        expected.add(new Header("HeaderA", "ValueA"));
        expected.add(new Header("HeaderB", "ValueB_1"));
        expected.add(new Header("HeaderB", "ValueB_2"));
        assertEquals(expected, result);
    }