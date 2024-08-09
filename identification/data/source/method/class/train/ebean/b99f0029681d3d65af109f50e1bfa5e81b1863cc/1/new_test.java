  @Test
  public void forJson() {

    JsonParser parser = getParser();
    JsonBeanReader<Customer> beanReader = json.createBeanReader(Customer.class, parser, null);
    beanReader.read();

    JsonParser more = getParser();
    JsonBeanReader<Customer> moreReader = beanReader.forJson(more, true);

    Customer customer = moreReader.read();
    assertThat(customer.getId()).isEqualTo(42);
    assertThat(customer.getName()).isEqualTo("dummy");
  }