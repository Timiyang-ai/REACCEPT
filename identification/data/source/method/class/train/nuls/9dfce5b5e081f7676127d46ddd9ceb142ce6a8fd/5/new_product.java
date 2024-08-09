public Result saveAlias(AliasPo aliaspo) throws NulsException {
        try {
            Result result = aliasStorageService.saveAlias(aliaspo);
            if (result.isFailed()) {
                this.rollbackAlias(aliaspo);
            }
            AccountPo po = accountStorageService.getAccount(aliaspo.getAddress()).getData();
            if (null == po) {
                return Result.getFailed(AccountErrorCode.ACCOUNT_NOT_EXIST);
            }
            po.setAlias(aliaspo.getAlias());
            Result resultAcc = accountStorageService.updateAccount(po);
            if (resultAcc.isFailed()) {
                this.rollbackAlias(aliaspo);
            }
        } catch (Exception e) {
            this.rollbackAlias(aliaspo);
            Log.error(e);
            return Result.getFailed(AccountErrorCode.FAILED);
        }
        return Result.getSuccess();
    }