private static void printWallets(List<Wallet> wallets) {
        reporter.message("");

        List<Contract> foundContracts = new ArrayList<>();
        for (Wallet wallet : wallets) {
            foundContracts.addAll(wallet.getContracts());

            reporter.message("found wallet: " + wallet.toString());
            reporter.verbose("");

            HashSet<WalletValueModel> balance = new HashSet<>();
            for (Contract contract : wallet.getContracts()) {
                try {
                    Decimal numcoins = new Decimal(contract.getStateData().getStringOrThrow(AMOUNT_FIELD_NAME));
                    SplitJoinPermission sjp = (SplitJoinPermission)contract.getPermissions().get("split_join").iterator().next();
                    WalletValueModel walletValueModel = null;
                    for (WalletValueModel wvm : balance) {
                        if (sjp.validateMergeFields(contract, wvm.contract))
                            walletValueModel = wvm;
                    }
                    if (walletValueModel == null)
                        walletValueModel = new WalletValueModel();
                    walletValueModel.value = walletValueModel.value.add(numcoins);
                    String currencyTag = contract.getDefinition().getData().getString("currency_code", null);
                    if (currencyTag == null)
                        currencyTag = contract.getDefinition().getData().getString("unit_short_name", null);
                    if (currencyTag == null)
                        currencyTag = contract.getDefinition().getData().getString("short_currency", null);
                    if (currencyTag == null)
                        currencyTag = contract.getDefinition().getData().getString("currency", null);
                    if (currencyTag == null)
                        currencyTag = contract.getDefinition().getData().getString("name", null);
                    if (currencyTag == null)
                        currencyTag = contract.getDefinition().getData().getString("unit_name", null);
                    if (currencyTag == null)
                        currencyTag = contract.getOrigin().toString();
                    walletValueModel.tag = currencyTag;
                    walletValueModel.contract = contract;
                    balance.add(walletValueModel);
                    reporter.verbose("found coins: " + contract.getOrigin().toString() + " -> " + numcoins + " (" + currencyTag + ") ");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            reporter.verbose("");
            reporter.message("total in the wallet: ");
            for (WalletValueModel w : balance) {
                reporter.message(w.value + " (" + w.tag + ") ");
            }
        }
    }