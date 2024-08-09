    @Test
    public void containsRow() {
        // given:
        Map<String, Object> row1 = new HashMap<>(2);
        row1.put("name", "alice");
        row1.put("age", 30);

        Map<String, Object> row2 = new HashMap<>(2);
        row2.put("name", "bob");
        row2.put("age", 31);

        Map<String, Object> row3 = new HashMap<>(2);
        row3.put("name", "carol");
        row3.put("age", 42);

        Map<String, Object> row4 = new HashMap<>(2);
        row4.put("name", "dave");
        row4.put("age", 55);

        Platform.runLater(() -> tableView.setItems(observableArrayList(row1, row2, row3, row4)));
        WaitForAsyncUtils.waitForFxEvents();

        // then:
        assertThat(tableView, TableViewMatchers.containsRow("alice", 30));
        assertThat(tableView, TableViewMatchers.containsRow("bob", 31));
        assertThat(tableView, TableViewMatchers.containsRow("carol", 42));
        assertThat(tableView, TableViewMatchers.containsRow("dave", 55));
        assertThat(tableView, not(TableViewMatchers.containsRow("ebert", 49)));
    }