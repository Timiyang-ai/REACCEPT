  @Test
  public void getId() {
    assertThat(receiver.getId()).isEqualTo("cluster");

    receiver.setGroup("group");
    assertThat(receiver.getId()).isEqualTo("group");
  }