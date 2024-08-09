public Result setPassword(String address, String password) {
        if (!Address.validAddress(address)) {
            return Result.getFailed(AccountErrorCode.ADDRESS_ERROR);
        }
        if(StringUtils.isBlank(password)){
            return Result.getFailed(AccountErrorCode.PARAMETER_ERROR,"The password is required");
        }
        if (!StringUtils.validPassword(password)) {
            return Result.getFailed(AccountErrorCode.PASSWORD_FORMAT_WRONG);

        }
        Account account = accountService.getAccount(address).getData();
        if (null == account) {
            return Result.getFailed(AccountErrorCode.FAILED, "The account not exist, address:" + address);
        }
        if(account.isEncrypted()){
            return Result.getFailed(AccountErrorCode.ACCOUNT_IS_ALREADY_ENCRYPTED, "This account already has a password.");
        }
        try {
            account.encrypt(password);
            Result result = accountStorageService.updateAccount(new AccountPo(account));
            if(result.isFailed()){
                return Result.getFailed(AccountErrorCode.FAILED);
            }
        } catch (NulsException e) {
            Log.error(e);
            return Result.getFailed();
        }
        return Result.getSuccess();
    }