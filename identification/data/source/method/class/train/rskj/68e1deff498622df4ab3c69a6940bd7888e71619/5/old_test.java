    @Test
    public void saveFederationElection() throws IOException {
        ABICallElection electionMock = mock(ABICallElection.class);
        List<Integer> storageBytesCalls = new ArrayList<>();
        List<Integer> serializeCalls = new ArrayList<>();
        PowerMockito.mockStatic(BridgeSerializationUtils.class);
        Repository repositoryMock = mock(Repository.class);
        BridgeStorageProvider storageProvider = new BridgeStorageProvider(repositoryMock, mockAddress("aabbccdd"), config.getNetworkConstants().getBridgeConstants(), activationsBeforeFork);

        PowerMockito.when(BridgeSerializationUtils.serializeElection(any(ABICallElection.class))).then((InvocationOnMock invocation) -> {
            ABICallElection election = invocation.getArgument(0);
            Assert.assertSame(electionMock, election);
            serializeCalls.add(0);
            return Hex.decode("aabb");
        });
        Mockito.doAnswer((InvocationOnMock invocation) -> {
            storageBytesCalls.add(0);
            RskAddress contractAddress = invocation.getArgument(0);
            DataWord address = invocation.getArgument(1);
            byte[] data = invocation.getArgument(2);
            // Make sure the bytes are set to the correct address in the repo and that what's saved is what was serialized
            Assert.assertTrue(Arrays.equals(new byte[]{(byte)0xaa, (byte)0xbb, (byte)0xcc, (byte)0xdd}, contractAddress.getBytes()));
            Assert.assertEquals(DataWord.valueOf("federationElection".getBytes(StandardCharsets.UTF_8)), address);
            Assert.assertTrue(Arrays.equals(Hex.decode("aabb"), data));
            return null;
        }).when(repositoryMock).addStorageBytes(any(RskAddress.class), any(DataWord.class), any(byte[].class));

        storageProvider.saveFederationElection();
        // Shouldn't have tried to save nor serialize anything
        Assert.assertEquals(0, storageBytesCalls.size());
        Assert.assertEquals(0, serializeCalls.size());
        Whitebox.setInternalState(storageProvider, "federationElection", electionMock);
        storageProvider.saveFederationElection();
        Assert.assertEquals(1, storageBytesCalls.size());
        Assert.assertEquals(1, serializeCalls.size());
    }