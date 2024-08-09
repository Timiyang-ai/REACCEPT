  @Test
  public void merge() {
    List<DependencyLink> links = asList(
      DependencyLink.newBuilder().parent("foo").child("bar").callCount(2L).errorCount(1L).build(),
      DependencyLink.newBuilder().parent("foo").child("bar").callCount(2L).errorCount(2L).build(),
      DependencyLink.newBuilder().parent("foo").child("foo").callCount(1L).build()
    );

    assertThat(DependencyLinker.merge(links)).containsExactly(
      DependencyLink.newBuilder().parent("foo").child("bar").callCount(4L).errorCount(3L).build(),
      DependencyLink.newBuilder().parent("foo").child("foo").callCount(1L).build()
    );
  }