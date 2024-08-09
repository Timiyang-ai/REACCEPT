    @Test
    public void containsItemsInOrder() {
        // in order
        assertThat(comboBox, ComboBoxMatchers.containsItemsInOrder("alice", "bob", "carol", "dave"));
        // partial (but in-order)
        assertThat(comboBox, ComboBoxMatchers.containsItemsInOrder("bob", "carol", "dave"));
    }