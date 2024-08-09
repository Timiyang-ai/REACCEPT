    @Test
    public void isNotFocused() throws Exception {
        // given:
        FxToolkit.setupSceneRoot(() -> {
            textField = new TextField("foo");
            textField2 = new TextField("bar");
            return new StackPane(textField, textField2);
        });

        Platform.runLater(() -> textField2.requestFocus());
        WaitForAsyncUtils.waitForFxEvents();

        // then:
        assertThat(textField, NodeMatchers.isNotFocused());
    }