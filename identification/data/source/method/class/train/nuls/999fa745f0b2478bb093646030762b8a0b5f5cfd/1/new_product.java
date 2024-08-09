public Result setPassword(String address, String password) {
        if (!Address.validAddress(address)) {
            return Result.getFailed(AccountErrorCode.ADDRESS_ERROR);
        }
        if(StringUtils.isBlank(password)){
            return Result.getFailed(AccountErrorCode.PARAMETER_ERROR,"The password is required");
        }
        if (!StringUtils.validPassword(password)) {
            return new Result(false, "Length between 8 and 20, the combination of characters and numbers");
        }
        Account account = accountService.getAccount(address).getData();
        if (null == account) {
            return Result.getFailed(AccountErrorCode.FAILED, "The account not exist, address:" + address);
        }
        if(account.isEncrypted()){
            return Result.getFailed(AccountErrorCode.ACCOUNT_IS_ALREADY_ENCRYPTED, "The account has been set to password.");
        }
        try {
            account.encrypt(password);
            accountStorageService.updateAccount(new AccountPo(account));
        } catch (NulsException e) {
            Log.error(e);
            return Result.getFailed();
        }
        return Result.getSuccess();
    }