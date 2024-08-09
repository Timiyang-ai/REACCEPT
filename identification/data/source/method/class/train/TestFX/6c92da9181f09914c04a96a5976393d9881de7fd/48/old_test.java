    @Test
    public void hasChildren() throws Exception {
        // given:
        Node parent = FxToolkit.setupFixture(() -> new StackPane(
                new Label("foo"), new Button("bar"), new Button("baz")));

        // then:
        assertThat(parent, NodeMatchers.hasChildren(2, ".button"));
    }