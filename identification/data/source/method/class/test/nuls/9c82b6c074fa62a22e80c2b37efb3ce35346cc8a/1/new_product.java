public Result<Boolean> setAlias(String addr, String password, String aliasName) {
        if (!Address.validAddress(addr)) {
            Result.getFailed(AccountErrorCode.PARAMETER_ERROR);
        }
        Account account = accountCacheService.getAccountByAddress(addr);
        if (null == account) {
            account = accountService.getAccount(addr).getData();
            if(null == account){
                return Result.getFailed(AccountErrorCode.ACCOUNT_NOT_EXIST);
            }
            try {
                if(account.isEncrypted()){
                    if(!account.unlock(password)){
                        return Result.getFailed(AccountErrorCode.PASSWORD_IS_WRONG);
                    }
                }
            } catch (NulsException e) {
                return Result.getFailed(AccountErrorCode.PASSWORD_IS_WRONG);
            }
        }
        if (StringUtils.isNotBlank(account.getAlias())) {
            return new Result(false, AccountErrorCode.ACCOUNT_ALREADY_SET_ALIAS, "Alias has been set up");
        }
        if (!StringUtils.validAlias(aliasName)) {
            return new Result(false, "The alias is between 3 to 20 characters");
        }
        if (isAliasExist(aliasName)) {
            Result.getFailed(AccountErrorCode.ALIAS_EXIST);
        }
        byte[] addressBytes = account.getAddress().getBase58Bytes();
        try {
            //创建一笔设置别名的交易
            AliasTransaction tx = new AliasTransaction();
            tx.setTime(System.currentTimeMillis());
            Alias alias = new Alias(addressBytes, aliasName);
            tx.setTxData(alias);
            tx.setHash(NulsDigestData.calcDigestData(tx.serialize()));

            CoinDataResult coinDataResult = accountLedgerService.getCoinData(addressBytes, AccountConstant.ALIAS_NA, tx.size());
            if(!coinDataResult.isEnough()){
                Result.getFailed(AccountErrorCode.INSUFFICIENT_BALANCE);
            }
            CoinData coinData = new CoinData();
            coinData.setFrom(coinDataResult.getCoinList());
            Coin change = coinDataResult.getChange();
            if (null != change) {
                //创建toList
                List<Coin> toList = new ArrayList<>();
                toList.add(change);
                coinData.setTo(toList);
            }
            tx.setCoinData(coinData);
            NulsSignData nulsSignData = accountService.signData(tx.serializeForHash(), account, password);
            P2PKHScriptSig scriptSig = new P2PKHScriptSig(nulsSignData, account.getPubKey());
            tx.setScriptSig(scriptSig.serialize());
            TransactionMessage message = new TransactionMessage();
            message.setMsgBody(tx);
            messageBusService.receiveMessage(message, null);
            return Result.getSuccess();
        } catch (Exception e) {
            Log.error(e);
            return new Result(false, e.getMessage());
        }
    }