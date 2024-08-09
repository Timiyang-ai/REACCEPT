public Result setPassword(String address, String password) {
        if (!AddressTool.validAddress(address)) {
            return Result.getFailed(AccountErrorCode.ADDRESS_ERROR);
        }
        if (StringUtils.isBlank(password)) {
            return Result.getFailed(AccountErrorCode.PARAMETER_ERROR);
        }
        if (!StringUtils.validPassword(password)) {
            return Result.getFailed(AccountErrorCode.PASSWORD_FORMAT_WRONG);

        }
        Account account = accountService.getAccount(address).getData();
        if (null == account) {
            return Result.getFailed(AccountErrorCode.ACCOUNT_NOT_EXIST);
        }
        if (account.isEncrypted()) {
            return Result.getFailed(AccountErrorCode.ACCOUNT_IS_ALREADY_ENCRYPTED);
        }
        try {
            account.encrypt(password);
            Result result = accountStorageService.updateAccount(new AccountPo(account));
            if (result.isFailed()) {
                return Result.getFailed(AccountErrorCode.FAILED);
            }
            accountCacheService.localAccountMaps.put(account.getAddress().getBase58(), account);
        } catch (NulsException e) {
            Log.error(e);
            return Result.getFailed(AccountErrorCode.FAILED);
        }
        return Result.getSuccess().setData(true);
    }