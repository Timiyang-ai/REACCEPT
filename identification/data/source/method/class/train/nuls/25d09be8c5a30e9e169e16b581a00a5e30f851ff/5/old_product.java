public Result getPrivateKey(String address, String password) {
        if (!Address.validAddress(address)) {
            return Result.getFailed(AccountErrorCode.ADDRESS_ERROR);
        }
        Account account = accountService.getAccount(address).getData();

        if (!account.isLocked()) {
            return Result.getSuccess().setData(Hex.encode(account.getPriKey()));
        } else {
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
        }
    }