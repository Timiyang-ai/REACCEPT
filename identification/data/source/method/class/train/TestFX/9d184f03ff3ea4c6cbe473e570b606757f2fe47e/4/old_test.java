    @Test
    @Ignore("See https://github.com/TestFX/TestFX/pull/284 for details")
    public void isFocused() throws Exception {
        Window window = FxToolkit.setupFixture(() -> {
            Stage stage = new Stage();
            stage.show();
            stage.requestFocus();
            return stage;
        });

        assertThat(window, WindowMatchers.isFocused());
    }