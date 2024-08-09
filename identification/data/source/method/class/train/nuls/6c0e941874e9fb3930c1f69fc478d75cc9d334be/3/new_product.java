public Result changePassword(String address, String oldPassword, String newPassword) {
        if (!Address.validAddress(address)) {
            return Result.getFailed(AccountErrorCode.ADDRESS_ERROR);
        }
        if(StringUtils.isBlank(oldPassword)){
            return Result.getFailed(AccountErrorCode.PARAMETER_ERROR,"The oldPassword is required");
        }
        if(StringUtils.isBlank(newPassword)){
            return Result.getFailed(AccountErrorCode.PARAMETER_ERROR,"The newPassword is required");
        }
        if (!StringUtils.validPassword(oldPassword)) {
            return Result.getFailed(AccountErrorCode.PASSWORD_IS_WRONG, "oldPassword Length between 8 and 20, the combination of characters and numbers");
        }
        if (!StringUtils.validPassword(newPassword)) {
            return Result.getFailed(AccountErrorCode.PASSWORD_IS_WRONG, "newPassword Length between 8 and 20, the combination of characters and numbers");
        }
        Account account = accountService.getAccount(address).getData();
        if (null == account) {
            return Result.getFailed(AccountErrorCode.ACCOUNT_NOT_EXIST, "The account not exist, address:" + address);
        }
        try {
            if (!account.isEncrypted()) {
                return Result.getFailed(AccountErrorCode.FAILED, "No password has been set up yet");
            }
            if (!account.unlock(oldPassword)) {
                return Result.getFailed(AccountErrorCode.PASSWORD_IS_WRONG, "old password error");
            }
            account.encrypt(newPassword, true);
            AccountPo po = new AccountPo(account);
            return accountStorageService.updateAccount(po);
        } catch (Exception e) {
            Log.error(e);
            return Result.getFailed(AccountErrorCode.FAILED, "The oldPassword is wrong, change password failed");
        }
    }