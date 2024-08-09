  @Test
  public void getBeanType() {
    assertThat(beanType(Order.class).getBeanType()).isEqualTo(Order.class);
  }