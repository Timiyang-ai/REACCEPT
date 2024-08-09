    @Test
    public void hasChild() throws Exception {
        // given:
        Node parent = FxToolkit.setupFixture(() -> new StackPane(
                new Label("foo"), new Button("bar"), new Button("baz")));

        // then:
        assertThat(parent, NodeMatchers.hasChild(".button"));
    }