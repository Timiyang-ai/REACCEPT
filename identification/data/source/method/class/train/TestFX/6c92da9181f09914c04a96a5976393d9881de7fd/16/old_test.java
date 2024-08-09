    @Test
    public void isEmpty() {
        // given:
        Platform.runLater(() -> listView.getItems().clear());
        WaitForAsyncUtils.waitForFxEvents();

        // then:
        assertThat(listView, ListViewMatchers.isEmpty());
    }