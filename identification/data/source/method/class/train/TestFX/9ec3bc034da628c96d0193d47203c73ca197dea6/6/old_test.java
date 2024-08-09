    @Test
    public void isNotShowing() throws Exception {
        Window window = FxToolkit.setupFixture((Callable<Stage>) Stage::new);
        assertWithCleanup(() -> assertThat(window, WindowMatchers.isNotShowing()));
    }