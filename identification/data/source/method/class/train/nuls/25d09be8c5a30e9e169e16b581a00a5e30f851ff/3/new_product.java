public Result getPrivateKey(String address, String password) {
        if (!AddressTool.validAddress(address)) {
            return Result.getFailed(AccountErrorCode.ADDRESS_ERROR);
        }
        Account account = accountService.getAccount(address).getData();
        if (null == account) {
            return Result.getFailed(AccountErrorCode.ACCOUNT_NOT_EXIST);
        }
        //加过密(有密码) 就验证密码 Already encrypted(Added password) and did not unlock, verify password
        if (account.isEncrypted()) {
            try {
                byte[] priKeyBytes = priKeyBytes = account.getPriKey(password);
                return Result.getSuccess().setData(Hex.encode(priKeyBytes));
            } catch (NulsException e) {
                return Result.getFailed(AccountErrorCode.PASSWORD_IS_WRONG);
            }
        }
        return Result.getFailed(AccountErrorCode.ACCOUNT_UNENCRYPTED);
    }