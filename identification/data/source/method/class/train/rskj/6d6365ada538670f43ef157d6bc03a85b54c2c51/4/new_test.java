    @Test
    public void saveOldFederation_preMultikey() throws IOException {
        Federation oldFederation = buildMockFederation(100, 200, 300);
        List<Integer> storageBytesCalls = new ArrayList<>();
        List<Integer> serializeCalls = new ArrayList<>();
        PowerMockito.mockStatic(BridgeSerializationUtils.class);
        useOriginalIntegerSerialization();
        Repository repositoryMock = mock(Repository.class);
        BridgeStorageProvider storageProvider = new BridgeStorageProvider(repositoryMock, mockAddress("aabbccdd"), config.getNetworkConstants().getBridgeConstants(), activationsBeforeFork);

        PowerMockito.when(BridgeSerializationUtils.serializeFederationOnlyBtcKeys(any(Federation.class))).then((InvocationOnMock invocation) -> {
            Federation federation = invocation.getArgument(0);
            Assert.assertEquals(oldFederation, federation);
            serializeCalls.add(0);
            return new byte[]{(byte)0xbb};
        });
        Mockito.doAnswer((InvocationOnMock invocation) -> {
            storageBytesCalls.add(0);
            RskAddress contractAddress = invocation.getArgument(0);
            DataWord address = invocation.getArgument(1);
            byte[] data = invocation.getArgument(2);
            // Make sure the bytes are set to the correct address in the repo and that what's saved is what was serialized
            Assert.assertTrue(Arrays.equals(new byte[]{(byte)0xaa, (byte)0xbb, (byte)0xcc, (byte)0xdd}, contractAddress.getBytes()));
            Assert.assertEquals(DataWord.fromString("oldFederation"), address);
            Assert.assertTrue(Arrays.equals(new byte[]{(byte)0xbb}, data));
            return null;
        }).when(repositoryMock).addStorageBytes(any(RskAddress.class), any(DataWord.class), any(byte[].class));

        storageProvider.saveOldFederation();
        // Shouldn't have tried to save nor serialize anything
        Assert.assertEquals(0, storageBytesCalls.size());
        Assert.assertEquals(0, serializeCalls.size());
        storageProvider.setOldFederation(oldFederation);
        storageProvider.saveOldFederation();
        Assert.assertEquals(1, storageBytesCalls.size());
        Assert.assertEquals(1, serializeCalls.size());
    }