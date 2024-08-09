    @Test
    public void containsExactlyItems() {
        // in order
        assertThat(comboBox, ComboBoxMatchers.containsExactlyItems("alice", "bob", "carol", "dave"));
        // not in order
        assertThat(comboBox, ComboBoxMatchers.containsExactlyItems("bob", "alice", "dave", "carol"));
    }