public Result changePassword(String address, String oldPassword, String newPassword) {
        if (!Address.validAddress(address)) {
            return Result.getFailed(AccountErrorCode.ADDRESS_ERROR);
        }
        if (!StringUtils.validPassword(oldPassword)) {
            return new Result(false, "Length between 8 and 20, the combination of characters and numbers");
        }
        if (!StringUtils.validPassword(newPassword)) {
            return new Result(false, "Length between 8 and 20, the combination of characters and numbers");
        }
        Account account = accountService.getAccount(address).getData();
        if (null != account) {
            Result.getFailed(AccountErrorCode.FAILED, "The account not exist, address:" + address);
        }
        try {
            if (!account.isEncrypted()) {
                return new Result(false, "No password has been set up yet");
            }
            if (!account.unlock(oldPassword)) {
                return new Result(false, "old password error");
            }
            account.encrypt(newPassword, true);
            AccountPo po = new AccountPo(account);
            return accountStorageService.updateAccount(po);
        } catch (Exception e) {
            Log.error(e);
            return  Result.getFailed(AccountErrorCode.FAILED, "change password failed");
        }
    }