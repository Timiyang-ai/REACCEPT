    @Test
    public void saveLockWhitelist() {
        LockWhitelist whitelistMock = mock(LockWhitelist.class);
        List<Integer> storageBytesCalls = new ArrayList<>();
        List<Integer> serializeCalls = new ArrayList<>();
        PowerMockito.mockStatic(BridgeSerializationUtils.class);
        Repository repositoryMock = mock(Repository.class);
        // Overriding activation to make sure it serializes the unlimited whitelist data
        BridgeStorageProvider storageProvider = new BridgeStorageProvider(repositoryMock, mockAddress("aabbccdd"), config.getNetworkConstants().getBridgeConstants(),
                activationsAllForks);

        // Mock the One-Off serialization
        PowerMockito
            .when(BridgeSerializationUtils.serializeOneOffLockWhitelist(any(Pair.class)))
            .then((InvocationOnMock invocation) -> {
                Pair<List<OneOffWhiteListEntry>, Integer> data = invocation.getArgument(0);
                Assert.assertEquals(whitelistMock.getAll(OneOffWhiteListEntry.class), data.getLeft());
                Assert.assertSame(whitelistMock.getDisableBlockHeight(), data.getRight());
                serializeCalls.add(0);
                return Hex.decode("ccdd");
            });

        Mockito
            .doAnswer((InvocationOnMock invocation) -> {
                storageBytesCalls.add(0);
                RskAddress contractAddress = invocation.getArgument(0);
                DataWord address = invocation.getArgument(1);
                byte[] data = invocation.getArgument(2);
                // Make sure the bytes are set to the correct address in the repo and that what's saved is what was serialized
                Assert.assertTrue(Arrays.equals(Hex.decode("aabbccdd"), contractAddress.getBytes()));
                Assert.assertEquals(DataWord.valueOf("lockWhitelist".getBytes(StandardCharsets.UTF_8)), address);
                Assert.assertTrue(Arrays.equals(Hex.decode("ccdd"), data));
                return null;
            })
            .when(repositoryMock).addStorageBytes(any(RskAddress.class), eq(DataWord.valueOf("lockWhitelist".getBytes(StandardCharsets.UTF_8))), any(byte[].class));

        // Mock the Unlimited serialization
        PowerMockito
            .when(BridgeSerializationUtils.serializeUnlimitedLockWhitelist(any(List.class)))
            .then((InvocationOnMock invocation) -> {
                List<UnlimitedWhiteListEntry> unlimitedWhiteListEntries = invocation.getArgument(0);
                Assert.assertEquals(whitelistMock.getAll(UnlimitedWhiteListEntry.class), unlimitedWhiteListEntries);
                serializeCalls.add(0);
                return Hex.decode("bbcc");
            });

        Mockito
            .doAnswer((InvocationOnMock invocation) -> {
                storageBytesCalls.add(0);
                RskAddress contractAddress = invocation.getArgument(0);
                DataWord address = invocation.getArgument(1);
                byte[] data = invocation.getArgument(2);
                // Make sure the bytes are set to the correct address in the repo and that what's saved is what was serialized
                Assert.assertTrue(Arrays.equals(Hex.decode("aabbccdd"), contractAddress.getBytes()));
                Assert.assertEquals(DataWord.valueOf("unlimitedLockWhitelist".getBytes(StandardCharsets.UTF_8)), address);
                Assert.assertTrue(Arrays.equals(Hex.decode("bbcc"), data));
                return null;
            })
            .when(repositoryMock).addStorageBytes(any(RskAddress.class), eq(DataWord.valueOf("unlimitedLockWhitelist".getBytes(StandardCharsets.UTF_8))), any(byte[].class));

        storageProvider.saveLockWhitelist();
        // Shouldn't have tried to save nor serialize anything
        Assert.assertEquals(0, storageBytesCalls.size());
        Assert.assertEquals(0, serializeCalls.size());
        Whitebox.setInternalState(storageProvider, "lockWhitelist", whitelistMock);
        storageProvider.saveLockWhitelist();
        Assert.assertEquals(2, storageBytesCalls.size());
        Assert.assertEquals(2, serializeCalls.size());
    }