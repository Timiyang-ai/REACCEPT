  @Test
  public void getCache_normal() {

    DefaultCacheHolder holder = new DefaultCacheHolder(options());

    DefaultServerCache cache = cache(holder, Customer.class);
    assertThat(cache.getName()).isEqualTo("org.tests.model.basic.Customer_B");
    assertThat(cache.getShortName()).isEqualTo("Customer_B");

    DefaultServerCache cache1 = cache(holder, Customer.class);
    assertThat(cache1).isSameAs(cache);

    DefaultServerCache cache2 = cache(holder, Contact.class);
    assertThat(cache1).isNotSameAs(cache2);
    assertThat(cache2.getName()).isEqualTo("org.tests.model.basic.Contact_B");
    assertThat(cache2.getShortName()).isEqualTo("Contact_B");

  }