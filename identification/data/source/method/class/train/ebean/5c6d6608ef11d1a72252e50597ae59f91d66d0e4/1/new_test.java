  @Test
  public void select_whenMultiple() throws Exception {

    OrmQueryDetail other = new OrmQueryDetail();
    other.select("id,name");

    OrmQueryProperties root = other.getChunk(null, false);
    assertNull(root.getPath());
    assertThat(root.getIncluded()).containsExactly("id", "name");
  }