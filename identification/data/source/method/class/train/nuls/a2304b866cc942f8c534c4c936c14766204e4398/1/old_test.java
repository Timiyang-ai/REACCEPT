    @Test
    public void exportAccountToKeyStore(){
        List<Account> accounts = this.accountService.createAccount(1, "nuls123456").getData();
        Account account = accounts.get(0);
        Result<AccountKeyStore> result = accountService.exportAccountToKeyStore(account.getAddress().toString(), "nuls123456");
        try {
            System.out.println(JSONUtils.obj2PrettyJson(result.getData()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(result.getData());

    }