    @Test
    public void containsExactlyItemsInOrder() {
        // in order
        assertThat(comboBox, ComboBoxMatchers.containsExactlyItemsInOrder("alice", "bob", "carol", "dave"));
    }