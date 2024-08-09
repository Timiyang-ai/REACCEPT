public Result<String> setAlias(String addr, String aliasName, String password) {
        if (!Address.validAddress(addr)) {
            Result.getFailed(AccountErrorCode.PARAMETER_ERROR);
        }
        Account account = accountService.getAccount(addr).getData();
        if (null == account) {
            return Result.getFailed(AccountErrorCode.ACCOUNT_NOT_EXIST);
        }
        if (account.isEncrypted() && account.isLocked()) {
            if (!account.validatePassword(password)) {
                return Result.getFailed(AccountErrorCode.PASSWORD_IS_WRONG);
            }
        }
        if (StringUtils.isNotBlank(account.getAlias())) {
            return Result.getFailed(AccountErrorCode.ACCOUNT_ALREADY_SET_ALIAS);
        }
        if (!StringUtils.validAlias(aliasName)) {
            return Result.getFailed(AccountErrorCode.ALIAS_FORMAT_WRONG);
        }
        if (!isAliasUsable(aliasName)) {
            return Result.getFailed(AccountErrorCode.ALIAS_EXIST);
        }
        byte[] addressBytes = account.getAddress().getBase58Bytes();
        try {
            //创建一笔设置别名的交易
            AliasTransaction tx = new AliasTransaction();
            tx.setTime(TimeService.currentTimeMillis());
            Alias alias = new Alias(addressBytes, aliasName);
            tx.setTxData(alias);

            CoinDataResult coinDataResult = accountLedgerService.getCoinData(addressBytes, AccountConstant.ALIAS_NA, tx.size() + P2PKHScriptSig.DEFAULT_SERIALIZE_LENGTH, TransactionFeeCalculator.OTHER_PRECE_PRE_1024_BYTES);
            if (!coinDataResult.isEnough()) {
                return Result.getFailed(AccountErrorCode.INSUFFICIENT_BALANCE);
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

            Coin coin = new Coin(NulsConstant.BLACK_HOLE_ADDRESS, Na.parseNuls(1), 0);
            coinData.addTo(coin);

            tx.setCoinData(coinData);
            tx.setHash(NulsDigestData.calcDigestData(tx.serializeForHash()));
            NulsSignData nulsSignData = accountService.signDigest(tx.getHash().getDigestBytes(), account, password);
            P2PKHScriptSig scriptSig = new P2PKHScriptSig(nulsSignData, account.getPubKey());
            tx.setScriptSig(scriptSig.serialize());
            Result saveResult = accountLedgerService.verifyAndSaveUnconfirmedTransaction(tx);
            if (saveResult.isFailed()) {
                return saveResult;
            }
            Result sendResult = this.transactionService.broadcastTx(tx);
            if (sendResult.isFailed()) {
                accountLedgerService.rollbackTransaction(tx);
                return sendResult;
            }
            String hash = tx.getHash().getDigestHex();
            return Result.getSuccess().setData(hash);
        } catch (Exception e) {
            Log.error(e);
            return Result.getFailed(e.getMessage());
        }
    }