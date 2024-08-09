@Test
  public void updateStateAsync_singleComponentC() {
    measureAndLayoutComponent(createSimpleTree());

    LayoutState current = mComponentTree.getMainThreadLayoutState();
    DefaultInternalNode layout = (DefaultInternalNode) current.getLayoutRoot();

    mComponentTree.updateStateAsync("root,B,C", createStateUpdate(), "test");
    mLayoutThreadShadowLooper.runToEndOfTasks();

    Set<String> set = new HashSet<>();
    set.add("root");
    set.add("root,A");
    set.add("root,B");
    set.add("root,B,D");
    assertCloneCalledForOnly(layout, set);
  }