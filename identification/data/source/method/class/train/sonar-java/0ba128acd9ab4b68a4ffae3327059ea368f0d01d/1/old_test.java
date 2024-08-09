  @Test
  public void test_node_parents() {
    ExplodedGraph eg = new ExplodedGraph();
    ExplodedGraph.Node child = eg.node(mockProgramPoint("child"), null);
    assertThat(child.edges()).isEmpty();
    assertThat(child.parent()).isNull();

    ExplodedGraph.Node parent = eg.node(mockProgramPoint("parent"), null);
    child.addParent(parent, null);
    assertThat(child.edges()).hasSize(1);
    ExplodedGraph.Edge edge = child.edges().iterator().next();
    assertThat(edge.parent).isEqualTo(parent);
    assertThat(child.parent()).isEqualTo(parent);

    // adding same parent twice
    child.addParent(parent, null);
    assertThat(child.edges()).hasSize(1);


    assertThat(child.parents()).hasSize(1);
    ExplodedGraph.Node parent2 = eg.node(mockProgramPoint("parent2"), null);
    child.addParent(parent2, null);
    assertThat(child.edges()).hasSize(2);
    assertThat(child.edges()).extracting("parent").contains(parent, parent2);
    assertThat(child.parents()).hasSize(2);
  }