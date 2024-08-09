  @Test
  public void test_convertRegistrar() {
    XjcRdeRegistrar bean = convertRegistrar(registrar);

    assertThat(bean.getId()).isEqualTo("GoblinMarket");
    assertThat(bean.getName()).isEqualTo("Maids heard the goblins cry: Come buy, come buy:");

    assertThat(bean.getPostalInfos()).hasSize(2);
    // I hard-coded the localized unicode happy address to come first just cuz.
    XjcRdeRegistrarPostalInfoType postalInfo0 = bean.getPostalInfos().get(0);
    assertThat(postalInfo0.getType()).isEqualTo(XjcRdeRegistrarPostalInfoEnumType.LOC);
    XjcRdeRegistrarAddrType address0 = postalInfo0.getAddr();
    assertThat(address0.getStreets()).containsExactly("123 Example Boulevard.");
    assertThat(address0.getCity()).isEqualTo("Hipsterville");
    assertThat(address0.getSp()).isEqualTo("NY");
    assertThat(address0.getPc()).isEqualTo("11211");
    assertThat(address0.getCc()).isEqualTo("US");
    // Now for the non-unicode form.
    XjcRdeRegistrarPostalInfoType postalInfo1 = bean.getPostalInfos().get(1);
    assertThat(postalInfo1.getType()).isEqualTo(XjcRdeRegistrarPostalInfoEnumType.INT);
    XjcRdeRegistrarAddrType address1 = postalInfo1.getAddr();
    assertThat(address1.getStreets()).containsExactly("123 Detonation Boulevard");
    assertThat(address1.getCity()).isEqualTo("Williamsburg");
    assertThat(address1.getSp()).isEqualTo("NY");
    assertThat(address1.getPc()).isEqualTo("11211");
    assertThat(address1.getCc()).isEqualTo("US");

    assertThat(bean.getVoice().getValue()).isEqualTo("+1.2125551212");
    assertThat(bean.getVoice().getX()).isNull();

    assertThat(bean.getFax().getValue()).isEqualTo("+1.2125551213");
    assertThat(bean.getFax().getX()).isNull();

    assertThat(bean.getEmail()).isEqualTo("contact-us@goblinmen.example");

    assertThat(bean.getUrl()).isEqualTo("http://www.goblinmen.example");

    assertThat(bean.getStatus()).isEqualTo(XjcRdeRegistrarStatusType.OK);

    assertThat(bean.getCrDate()).isEqualTo(DateTime.parse("2013-01-01T00:00:00Z"));

    assertThat(bean.getUpDate()).isEqualTo(DateTime.parse("2014-01-01T00:00:00Z"));

    assertThat(bean.getWhoisInfo().getName()).isEqualTo("whois.goblinmen.example");
  }