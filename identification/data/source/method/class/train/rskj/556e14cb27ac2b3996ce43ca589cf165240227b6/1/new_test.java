    @Test
    public void serializeLockWhitelist() throws Exception {
        PowerMockito.mockStatic(RLP.class);
        mock_RLP_encodeBigInteger();
        mock_RLP_encodeList();
        mock_RLP_encodeElement();

        byte[][] addressesBytes = new byte[][]{
                BtcECKey.fromPrivate(BigInteger.valueOf(100)).getPubKeyHash(),
                BtcECKey.fromPrivate(BigInteger.valueOf(200)).getPubKeyHash(),
                BtcECKey.fromPrivate(BigInteger.valueOf(300)).getPubKeyHash(),
                BtcECKey.fromPrivate(BigInteger.valueOf(400)).getPubKeyHash(),
                BtcECKey.fromPrivate(BigInteger.valueOf(500)).getPubKeyHash(),
                BtcECKey.fromPrivate(BigInteger.valueOf(600)).getPubKeyHash(),
        };
        Coin maxToTransfer = Coin.CENT;

        LockWhitelist lockWhitelist = new LockWhitelist(
            Arrays.stream(addressesBytes)
                .map(bytes -> new Address(NetworkParameters.fromID(NetworkParameters.ID_REGTEST), bytes))
                .collect(Collectors.toMap(Function.identity(), k -> new OneOffWhiteListEntry(k, maxToTransfer))),
                0);

        byte[] result = BridgeSerializationUtils.serializeOneOffLockWhitelist(Pair.of(
                lockWhitelist.getAll(OneOffWhiteListEntry.class),
                lockWhitelist.getDisableBlockHeight()
        ));
        StringBuilder expectedBuilder = new StringBuilder();
        Arrays.stream(addressesBytes).sorted(UnsignedBytes.lexicographicalComparator()).forEach(bytes -> {
            expectedBuilder.append("dd");
            expectedBuilder.append(Hex.toHexString(bytes));
            expectedBuilder.append("ff");
            expectedBuilder.append(Hex.toHexString(BigInteger.valueOf(maxToTransfer.value).toByteArray()));
        });
        expectedBuilder.append("ff00");
        byte[] expected = Hex.decode(expectedBuilder.toString());
        Assert.assertThat(result, is(expected));
    }