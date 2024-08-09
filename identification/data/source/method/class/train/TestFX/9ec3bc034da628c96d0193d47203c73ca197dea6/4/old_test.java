    @Test
    public void isNotFocused() throws Exception {
        Window window = FxToolkit.setupFixture((Callable<Stage>) Stage::new);
        assertWithCleanup(() -> assertThat(window, WindowMatchers.isNotFocused()));
    }