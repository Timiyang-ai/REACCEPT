    @Test
    public void test_getOffsetIndex1() {
        String input = "" +
                "Add: configuration for repeated prefixes in items, which would `be #2` copied when ⦙adding/splitting an item. In other words they\n" +
                "    would be treated equivalent to task item marker prefix. That way\n" +
                "    standard: `Add: `, `Fix: `, `Break: ` and `Deprecate: ` prefixes would be automatically copied." +
                "";

        String expected = "" +
                "OffsetInfo{ o=[83, 84), i=[83, 84) }\n" +
                "-----------------------------------------------------\n" +
                "⟦Add: configuration for repeated prefixes in items, which would `be⟧\n" +
                "⟦#2` copied when adding/splitting an item. In other words they\n" +
                "⟧⟦would be treated equivalent to task item marker prefix. That way\n" +
                "⟧⟦standard: `Add: `, `Fix: `, `Break: ` and `Deprecate: ` prefixes⟧\n" +
                "⟦would be automatically copied.⟧\n" +
                "-----------------------------------------------------\n" +
                "Add: configuration for repeated prefixes in items, which would `be\n" +
                "#2` copied when ⦙adding/splitting an item. In other words they\n" +
                "would be treated equivalent to task item marker prefix. That way\n" +
                "standard: `Add: `, `Fix: `, `Break: ` and `Deprecate: ` prefixes\n" +
                "would be automatically copied." +
                "";

        assertEquals(expected, wrapText(input, false, 66));
    }