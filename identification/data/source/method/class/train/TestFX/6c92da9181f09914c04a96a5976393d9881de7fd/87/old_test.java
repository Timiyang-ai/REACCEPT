    @Test
    public void containsItems() {
        // in order
        assertThat(comboBox, ComboBoxMatchers.containsItems("alice", "bob", "carol", "dave"));
        // not in order
        assertThat(comboBox, ComboBoxMatchers.containsItems("bob", "alice", "dave", "carol"));
        // partial
        assertThat(comboBox, ComboBoxMatchers.containsItems("bob", "alice", "dave"));
    }