    @Test
    public void isShowing() throws Exception {
        Window window = FxToolkit.setupFixture(() -> {
            Stage stage = new Stage();
            stage.show();
            return stage;
        });
        assertWithCleanup(() -> assertThat(window, WindowMatchers.isShowing()));
    }