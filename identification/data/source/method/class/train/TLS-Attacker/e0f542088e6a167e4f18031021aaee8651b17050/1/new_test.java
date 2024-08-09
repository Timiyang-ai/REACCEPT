@Test()
    @Category(SlowTests.class)
    public void testAttack() {
        CONSOLE.info("Starting ICEAttacker test... this may take some time");
        TestECOracle oracle = new TestECOracle(NamedGroup.SECP256R1);
        ICEAttacker attacker = new ICEAttacker(oracle, ICEAttacker.ServerType.ORACLE, 4, NamedGroup.SECP256R1);
        BigInteger result = attacker.attack();

        LOGGER.debug(result);
        LOGGER.debug(oracle.getPrivateKey());

        assertEquals(oracle.getPrivateKey(), result);
    }