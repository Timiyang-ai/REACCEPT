  @Test
  public void put_get() throws Exception {

    PersistenceContext pc = pc();
    pc.put(Customer.class, customer42.getId(), customer42);

    Object found = pc.get(Customer.class, 42);
    assertThat(found).isSameAs(customer42);
  }