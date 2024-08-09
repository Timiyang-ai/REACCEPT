    @Test
    public void changePassword() {
        List<Account> accounts = this.accountService.createAccount(1, "nuls123456").getData();
        Account account = accounts.get(0);
        accountBaseService.changePassword(account.getAddress().toString(),"nuls123456", "nuls111111");
        Account acc = accountService.getAccount(account.getAddress()).getData();
        try {
            assertFalse(acc.unlock("nuls123456"));
            assertTrue(acc.unlock("nuls111111"));
            assertArrayEquals(acc.getPriKey(), account.getPriKey());
        } catch (NulsException e) {

        }
    }