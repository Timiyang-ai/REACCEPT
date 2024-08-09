public Result changePassword(String address, String oldPassword, String newPassword) {
        if (!AddressTool.validAddress(address)) {
            return Result.getFailed(AccountErrorCode.ADDRESS_ERROR);
        }
        if (StringUtils.isBlank(oldPassword)) {
            return Result.getFailed(AccountErrorCode.PARAMETER_ERROR);
        }
        if (StringUtils.isBlank(newPassword)) {
            return Result.getFailed(AccountErrorCode.PARAMETER_ERROR);
        }
        if (!StringUtils.validPassword(oldPassword)) {
            return Result.getFailed(AccountErrorCode.PASSWORD_FORMAT_WRONG);
        }
        if (!StringUtils.validPassword(newPassword)) {
            return Result.getFailed(AccountErrorCode.PASSWORD_FORMAT_WRONG);
        }
        Account account = accountService.getAccount(address).getData();
        if (null == account) {
            return Result.getFailed(AccountErrorCode.ACCOUNT_NOT_EXIST);
        }
        try {
            if (!account.isEncrypted()) {
                return Result.getFailed(AccountErrorCode.ACCOUNT_UNENCRYPTED);
            }
            if (!account.validatePassword(oldPassword)) {
                return Result.getFailed(AccountErrorCode.PASSWORD_IS_WRONG);
            }
            account.unlock(oldPassword);
            account.encrypt(newPassword, true);
            AccountPo po = new AccountPo(account);
            Result result = accountStorageService.updateAccount(po);
            if (result.isFailed()) {
                return Result.getFailed(AccountErrorCode.FAILED);
            }
            accountCacheService.localAccountMaps.put(account.getAddress().getBase58(), account);
            return result.setData(true);
        } catch (NulsException e) {
            Log.error(e);
            return Result.getFailed(e.getErrorCode());
        }
    }