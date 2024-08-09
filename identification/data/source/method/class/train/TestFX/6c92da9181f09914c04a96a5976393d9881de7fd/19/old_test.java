    @Test
    public void hasChild() throws Exception {
        // given:
        Parent parent = FxToolkit.setupFixture(() -> new StackPane(
                new Label("foo"), new Button("bar"), new Button("baz")));

        // then:
        assertThat(parent, ParentMatchers.hasChild());
    }