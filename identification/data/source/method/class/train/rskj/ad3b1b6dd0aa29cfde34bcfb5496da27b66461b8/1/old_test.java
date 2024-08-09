    @Test
    public void addAccountWithPrivateKey() {
        Wallet wallet = WalletFactory.createWallet();
        byte[] privateKeyBytes = Keccak256Helper.keccak256("seed".getBytes());

        byte[] address = wallet.addAccountWithPrivateKey(privateKeyBytes);

        Assert.assertNotNull(address);

        byte[] calculatedAddress = ECKey.fromPrivate(Keccak256Helper.keccak256("seed".getBytes())).getAddress();

        Assert.assertArrayEquals(calculatedAddress, address);

        List<byte[]> addresses = wallet.getAccountAddresses();

        Assert.assertNotNull(addresses);
        Assert.assertFalse(addresses.isEmpty());
        Assert.assertEquals(1, addresses.size());

        byte[] addr = addresses.get(0);

        Assert.assertNotNull(addr);
        Assert.assertArrayEquals(address, addr);
    }