@Ignore
  @Test
  public void testRun_domainInTestTld_isConsideredNotFound() {
    persistResource(Registry.get("lol").asBuilder().setTldType(Registry.TldType.TEST).build());
    Registrar registrar = persistResource(makeRegistrar(
        "evilregistrar", "Yes Virginia", ACTIVE));
    persistResource(makeDomainResource(
        "cat.lol",
        persistResource(makeContactResource("5372808-ERL", "Goblin Market", "lol@cat.lol")),
        persistResource(makeContactResource("5372808-IRL", "Santa Claus", "BOFH@cat.lol")),
        persistResource(makeContactResource("5372808-TRL", "The Raven", "bog@cat.lol")),
        persistResource(makeHostResource("ns1.cat.lol", "1.2.3.4")),
        persistResource(makeHostResource("ns2.cat.lol", "bad:f00d:cafe::15:beef")),
        registrar));
    persistSimpleResources(makeRegistrarContacts(registrar));
    newWhoisAction("domain cat.lol\r\n").run();
    assertThat(response.getStatus()).isEqualTo(200);
    assertThat(response.getPayload()).isEqualTo(loadFile("whois_action_domain_not_found.txt"));
  }