    @Test
    public void anything() throws Exception {
        List<Node> nodes = FxToolkit.setupFixture(() -> {
            List<Node> temp = new ArrayList<>(3);
            temp.add(new Region());
            temp.add(new Button("foo"));
            temp.add(new TextField("bar"));
            return temp;
        });

        assertThat(from(nodes).match(NodeMatchers.anything()).queryAll(), hasItem(nodes.get(1)));
    }