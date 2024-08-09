  @Test
  public void test_wereRegistrarsModified_noRegistrars_returnsFalse() {
    assertThat(newSyncRegistrarsSheet().wereRegistrarsModified()).isFalse();
  }