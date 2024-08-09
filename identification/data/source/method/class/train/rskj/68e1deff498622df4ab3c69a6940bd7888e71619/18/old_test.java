    @Test
    public void serializeElection() throws Exception {
        PowerMockito.mockStatic(RLP.class);
        mock_RLP_encodeElement();
        mock_RLP_encodeList();

        AddressBasedAuthorizer mockedAuthorizer = mock(AddressBasedAuthorizer.class);
        when(mockedAuthorizer.isAuthorized(any(RskAddress.class))).thenReturn(true);

        Map<ABICallSpec, List<RskAddress>> sampleVotes = new HashMap<>();
        sampleVotes.put(
                new ABICallSpec("one-function", new byte[][]{}),
                Arrays.asList(mockAddress("8899"), mockAddress("aabb"))
        );
        sampleVotes.put(
                new ABICallSpec("another-function", new byte[][]{ Hex.decode("01"), Hex.decode("0203") }),
                Arrays.asList(mockAddress("ccdd"), mockAddress("eeff"), mockAddress("0011"))
        );
        sampleVotes.put(
                new ABICallSpec("yet-another-function", new byte[][]{ Hex.decode("0405") }),
                Arrays.asList(mockAddress("fa"), mockAddress("ca"))
        );

        ABICallElection sample = new ABICallElection(mockedAuthorizer, sampleVotes);

        byte[] result = BridgeSerializationUtils.serializeElection(sample);
        String hexResult = Hex.toHexString(result);

        StringBuilder expectedBuilder = new StringBuilder();

        expectedBuilder.append("dd");
        expectedBuilder.append(Hex.toHexString("another-function".getBytes(StandardCharsets.UTF_8)));
        expectedBuilder.append("dd01dd0203");
        expectedBuilder.append("dd0011ddccddddeeff");

        expectedBuilder.append("dd");
        expectedBuilder.append(Hex.toHexString("one-function".getBytes(StandardCharsets.UTF_8)));
        expectedBuilder.append("dd8899ddaabb");

        expectedBuilder.append("dd");
        expectedBuilder.append(Hex.toHexString("yet-another-function".getBytes(StandardCharsets.UTF_8)));
        expectedBuilder.append("dd0405");
        expectedBuilder.append("ddcaddfa");

        assertEquals(expectedBuilder.toString(), hexResult);
    }