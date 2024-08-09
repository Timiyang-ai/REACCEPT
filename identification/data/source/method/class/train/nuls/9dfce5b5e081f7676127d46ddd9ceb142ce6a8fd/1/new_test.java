    @Test
    public void saveAlias() {
        List<Account> accounts = accountService.createAccount(1, "nuls123456").getData();
        Account account = accounts.get(0);
        Alias alias = new Alias(account.getAddress().getAddressBytes(), "lichao");
        try {
            assertTrue(aliasService.saveAlias(new AliasPo(alias)).isSuccess());
        } catch (NulsException e) {
            e.printStackTrace();
        }
    }