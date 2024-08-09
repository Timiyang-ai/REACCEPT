  @Test
  public void test_toObject() {

    JsonContext json = DB.getDefault().json();

    Customer customer = new Customer();
    customer.setId(1);
    customer.setName("Jim");

    String asJson = json.toJson(customer);

    Object bean = json.toObject(Customer.class, asJson);

    assertTrue(bean instanceof Customer);
    assertEquals(Integer.valueOf(1), ((Customer) bean).getId());
    assertEquals("Jim", ((Customer) bean).getName());

    StringReader reader = new StringReader(asJson);
    bean = json.toObject(Customer.class, reader);
    assertTrue(bean instanceof Customer);
    assertEquals(Integer.valueOf(1), ((Customer) bean).getId());
    assertEquals("Jim", ((Customer) bean).getName());
  }