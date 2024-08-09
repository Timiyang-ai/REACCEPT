  @Test
  public void lazyLoadMany_addsToParentCollection() {

    Customer customer = new Customer();
    customer.setContacts(new BeanList<>());

    Contact contact = new Contact();
    contact.setCustomer(customer);

    contacts().lazyLoadMany((EntityBean) contact);

    assertThat(customer.getContacts()).hasSize(1);
    assertThat(customer.getContacts().get(0)).isSameAs(contact);
  }