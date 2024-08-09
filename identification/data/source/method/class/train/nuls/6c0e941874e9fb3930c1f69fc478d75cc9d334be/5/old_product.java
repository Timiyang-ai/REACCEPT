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

       /* List<Account> accounts = accountService.getAccountList().getData();
        if (accounts == null || accounts.isEmpty()) {
            new Result(false, "No account was found");
        }*/

        try {
            if (!account.isEncrypted()) {
                return new Result(false, "No password has been set up yet");
            }

//            List<AccountPo> accountPoList = new ArrayList<>();
//            for (Account account : accounts) {
                if (!account.unlock(oldPassword)) {
                    return new Result(false, "old password error");
                }
                account.encrypt(newPassword, true);

                AccountPo po = new AccountPo(account);
//                AccountTool.toPojo(account, po);
//                accountPoList.add(po);
//            }

//            if (accountPoList.size() > 0) {
//                accountDao.update(accountPoList);
//            }
            accountStorageService.updateAccount(po);
            accountCacheService.putAccountList(accounts);
        } catch (Exception e) {
            Log.error(e);
            return new Result(false, "change password failed");
        }
        this.eventBroadcaster.publishToLocal(new PasswordChangeNotice());
        return new Result(true, "OK");
    }