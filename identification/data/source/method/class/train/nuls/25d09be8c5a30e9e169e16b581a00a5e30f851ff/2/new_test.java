    @Test
    public void getPrivateKeyTest() {
        List<Account> accounts = this.accountService.createAccount(1, "nuls123456").getData();
        Account account = accounts.get(0);
        Result result = accountBaseService.getPrivateKey(account.getAddress().toString(), "nuls123456");
        assertTrue(result.isSuccess());
        try {
            account.unlock("nuls123456");
        } catch (NulsException e) {
            e.printStackTrace();
        }
        assertArrayEquals(Hex.decode((String)result.getData()), account.getPriKey());

        List<Account> accounts2 = this.accountService.createAccount(1, "").getData();
        Account account2 = accounts2.get(0);
        Result result2 = accountBaseService.getPrivateKey(account2.getAddress().toString(), "");
        assertTrue(result2.isSuccess());
        assertArrayEquals(Hex.decode((String)result2.getData()), account2.getPriKey());
    }