    @Test
    public void testToString() {
        String result = IterableUtils.toString(iterableA);
        assertEquals("[1, 2, 2, 3, 3, 3, 4, 4, 4, 4]", result);

        result = IterableUtils.toString(new ArrayList<Integer>());
        assertEquals("[]", result);

        result = IterableUtils.toString(null);
        assertEquals("[]", result);

        result = IterableUtils.toString(iterableA, input -> new Integer(input * 2).toString());
        assertEquals("[2, 4, 4, 6, 6, 6, 8, 8, 8, 8]", result);

        result = IterableUtils.toString(new ArrayList<Integer>(), input -> {
            fail("not supposed to reach here");
            return "";
        });
        assertEquals("[]", result);

        result = IterableUtils.toString(null, input -> {
            fail("not supposed to reach here");
            return "";
        });
        assertEquals("[]", result);
    }