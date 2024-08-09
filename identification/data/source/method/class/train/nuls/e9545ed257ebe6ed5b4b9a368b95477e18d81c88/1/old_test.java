    @Test
    public void getAccount(){
        List<Account> accounts = this.accountService.createAccount(2, "nuls123456").getData();
        Account account = accounts.get(0);
        assertNotNull(accountService.getAccount(account.getAddress()).getData());
        assertEquals(accountService.getAccount(account.getAddress()).getData().getAddress().toString(), account.getAddress().toString());

        Account acc1 = accountService.getAccount(account.getAddress().toString()).getData();
        assertNotNull(acc1);
        assertEquals(acc1.getAddress().toString(), account.getAddress().toString());

        Account acc2 = accountService.getAccount(account.getAddress().getAddressBytes()).getData();
        assertNotNull(acc2);
        assertEquals(acc2.getAddress().toString(), account.getAddress().toString());
    }