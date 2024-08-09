    @Test
    public void setAlias() {
        List<Account> accounts = accountService.createAccount(1, "nuls123456").getData();
        Account account = accounts.get(0);
        Result result = aliasService.setAlias(account.getAddress().toString(), "nuls123456", "Charlie555");
        assertTrue(result.isSuccess());
    }