private void activate(String nodeCipherSuite, String utilityCipherSuite, int expRes) throws Exception {
        cipherSuites = F.isEmpty(nodeCipherSuite) ? null : nodeCipherSuite.split(",");

        Ignite ignite = startGrids(1);

        assertFalse(ignite.cluster().active());

        final CommandHandler cmd = new CommandHandler();

        List<String> params = new ArrayList<>();

        params.add("--keystore");
        params.add(GridTestUtils.keyStorePath("node01"));
        params.add("--keystore-password");
        params.add(GridTestUtils.keyStorePassword());

        if (!F.isEmpty(utilityCipherSuite)) {
            params.add("--ssl-cipher-suites");
            params.add(utilityCipherSuite);
        }

        params.add("--activate");

        assertEquals(expRes, execute(params));

        if (expRes == EXIT_CODE_OK)
            assertTrue(ignite.cluster().active());
        else
            assertFalse(ignite.cluster().active());

        assertEquals(EXIT_CODE_CONNECTION_FAILED, cmd.execute(Arrays.asList("--deactivate", "--yes")));
    }