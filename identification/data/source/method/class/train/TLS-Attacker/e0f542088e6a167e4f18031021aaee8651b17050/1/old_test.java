@Test()
    @Category(SlowTests.class)
    public void testAttack() {
        CONSOLE.info("Starting ICEAttacker test... this may take some time");
        TestECOracle oracle = new TestECOracle("secp256r1");
        ICEAttacker attacker = new ICEAttacker(oracle);
        attacker.attack();
        BigInteger result = attacker.getResult();

        LOGGER.debug(result);
        LOGGER.debug(oracle.getComputer().getSecret());

        assertEquals(oracle.getComputer().getSecret(), result);
    }