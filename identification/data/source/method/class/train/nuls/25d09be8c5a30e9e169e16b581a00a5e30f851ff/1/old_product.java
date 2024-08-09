public Result getPrivateKey(String address, String password) {
        if (!Address.validAddress(address)) {
            return Result.getFailed(AccountErrorCode.ADDRESS_ERROR);
        }
        Account account = accountService.getAccount(address).getData();
        if(null == account){
            return Result.getFailed(AccountErrorCode.ACCOUNT_NOT_EXIST);
        }
        //加过密(有密码)并且没有解锁, 就验证密码 Already encrypted(Added password) and did not unlock, verify password
        if (account.isEncrypted() && account.isLocked()) {
            try {
                if (!account.unlock(password)) {
                    return Result.getFailed(AccountErrorCode.PASSWORD_IS_WRONG);
                }
                byte[] priKeyBytes = account.getPriKey();
                account.lock();
                return Result.getSuccess().setData(Hex.encode(priKeyBytes));
            } catch (NulsException e) {
                return Result.getFailed(AccountErrorCode.PASSWORD_IS_WRONG);
            }
        } else {
            return Result.getSuccess().setData(Hex.encode(account.getPriKey()));
        }
    }