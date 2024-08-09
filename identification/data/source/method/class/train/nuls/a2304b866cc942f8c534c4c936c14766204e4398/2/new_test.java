    @Test
    public void removeAccount() {
        List<Account> accounts = this.accountService.createAccount(2, "nuls123456").getData();
        Result result0 = accountService.removeAccount(accounts.get(0).getAddress().toString(), "nuls123456");
        assertTrue(result0.isSuccess());
        Result result1 = accountService.removeAccount(accounts.get(1).getAddress().toString(), "123456");
        assertTrue(result1.isFailed());
    }