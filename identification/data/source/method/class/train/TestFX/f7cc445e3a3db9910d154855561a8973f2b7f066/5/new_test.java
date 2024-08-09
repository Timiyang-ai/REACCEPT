    @Test
    public void isFocused() throws Exception {
        // given:
        FxToolkit.setupSceneRoot(() -> {
            textField = new TextField("foo");
            return new StackPane(textField);
        });

        // when:
        Platform.runLater(() -> textField.requestFocus());
        WaitForAsyncUtils.waitForFxEvents();


        // then:
        textField.isFocused();
        assertThat(textField, NodeMatchers.isFocused());
    }