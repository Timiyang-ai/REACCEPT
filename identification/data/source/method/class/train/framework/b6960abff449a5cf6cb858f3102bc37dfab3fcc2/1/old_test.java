    @Test
    public void setValue() {
        selectToTest.setValue(Collections.singleton("1"));

        assertEquals(Collections.singleton("1"),
                selectToTest.getSelectedItems());

        Set<String> set = new LinkedHashSet<>();
        set.add("4");
        set.add("3");
        selectToTest.setValue(set);

        assertEquals(set, selectToTest.getSelectedItems());
        verifyValueChangeEvents();
    }