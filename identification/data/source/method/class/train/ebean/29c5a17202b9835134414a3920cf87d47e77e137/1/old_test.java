  @Test
  public void isPublishOrMerge() {

    assertThat(Flags.isPublishMergeOrNormal(0)).isFalse();
    assertThat(Flags.isPublishMergeOrNormal(Flags.INSERT)).isFalse();
    assertThat(Flags.isPublishMergeOrNormal(Flags.RECURSE)).isFalse();

    assertThat(Flags.isPublishMergeOrNormal(Flags.PUBLISH)).isTrue();
    assertThat(Flags.isPublishMergeOrNormal(Flags.MERGE)).isTrue();
    assertThat(Flags.isPublishMergeOrNormal(Flags.NORMAL)).isTrue();

    int mergePublish = Flags.setMerge(Flags.setPublish(0));
    assertThat(Flags.isPublishMergeOrNormal(mergePublish)).isTrue();
  }