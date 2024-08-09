  @Test
  public void test_supplier_success() {
    Supplier<String> a = Unchecked.supplier(() -> "A");
    assertThat(a.get()).isEqualTo("A");
  }