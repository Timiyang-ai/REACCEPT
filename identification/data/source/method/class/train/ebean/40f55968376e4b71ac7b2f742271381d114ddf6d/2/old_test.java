  @Test
  public void clear() throws Exception {

    PersistenceContext pc = pcWith42();
    pc.clear();
    assertThat(pc.size(Customer.class)).isEqualTo(0);
  }