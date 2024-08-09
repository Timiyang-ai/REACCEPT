    @Test
    public void setPassword() {
        List<Account> accounts = this.accountService.createAccount(1, "").getData();
        Account account = accounts.get(0);
        accountBaseService.setPassword(account.getAddress().toString(),"nuls123456");
        Account acc = accountService.getAccount(account.getAddress()).getData();
        try {
            assertTrue(acc.unlock("nuls123456"));
            assertArrayEquals(acc.getPriKey(), account.getPriKey());
        } catch (NulsException e) {
            e.printStackTrace();
        }
    }