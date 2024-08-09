@Test
  public void updateStateSync_singleComponentD() {
    measureAndLayoutComponent(createSimpleTree());

    LayoutState current = mComponentTree.getMainThreadLayoutState();
    DefaultInternalNode layout = (DefaultInternalNode) current.getLayoutRoot();

    mComponentTree.updateStateSync("root,B,D", createStateUpdate(), "test", false);

    Set<String> set = new HashSet<>();
    set.add("root");
    set.add("root,A");
    set.add("root,B");
    set.add("root,B,C");
    assertCloneCalledForOnly(layout, set);
  }