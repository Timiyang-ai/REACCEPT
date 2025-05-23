public Result<Boolean> setAlias(String addr, String password, String aliasName) {
        if (!Address.validAddress(addr)) {
            Result.getFailed(AccountErrorCode.DATA_PARSE_ERROR);
        }
        Account account = AccountCacheService.getAccountByAddress(addr);

        if (null == account) {
            account = accountService.getAccount(addr).getData();
            if(null == account){
                return Result.getFailed(AccountErrorCode.ACCOUNT_NOT_EXIST);
            }
            try {
                account.decrypt(password);
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
            //手续费 fee ////////暂时!!!!
            Na fee = Na.parseNuls(0.01);
            Na total = fee.add(AccountConstant.ALIAS_NA);
            Balance balance = accountLedgerService.getBalance(addressBytes).getData();
            if (balance.getUsable().isLessThan(total)) {
                Result.getFailed(AccountErrorCode.INSUFFICIENT_BALANCE);
            }
            List<Coin> fromList = accountLedgerService.getCoinData(addressBytes, total);
            Na totalFrom = Na.ZERO;
            for (Coin coin : fromList) {
                totalFrom = totalFrom.add(coin.getNa());
            }
            if (totalFrom.isLessThan(total)) {
                Result.getFailed(AccountErrorCode.INSUFFICIENT_BALANCE);
            }
            CoinData coinData = new CoinData();
            coinData.setFrom(fromList);
            if (totalFrom.isGreaterThan(total)) {
                //创建toList
                List<Coin> toList = new ArrayList<>();
                Na change = totalFrom.minus(total);
                toList.add(new Coin(addressBytes, change, 0));
                coinData.setTo(toList);
            }

            //创建一笔设置别名的交易
            AliasTransaction tx = new AliasTransaction();
            tx.setTime(System.currentTimeMillis());
            Alias alias = new Alias(addressBytes, aliasName);
            tx.setTxData(alias);
            tx.setCoinData(coinData);
            tx.setHash(NulsDigestData.calcDigestData(tx.serialize()));

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