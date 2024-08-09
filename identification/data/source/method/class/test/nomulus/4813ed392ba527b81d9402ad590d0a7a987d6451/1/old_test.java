  @Test
  public void test_getGroupEmailAddressForContactType_convertsToLowercase() {
    assertThat(getGroupEmailAddressForContactType(
            "SomeRegistrar",
            RegistrarContact.Type.ADMIN,
            "domain-registry.example"))
        .isEqualTo("someregistrar-primary-contacts@domain-registry.example");
  }