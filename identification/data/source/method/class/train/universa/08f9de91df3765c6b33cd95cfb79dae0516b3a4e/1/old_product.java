private static void printWallets(List<Wallet> wallets) {
        reporter.message("");

        List<Contract> foundContracts = new ArrayList<>();
        for (Wallet wallet : wallets) {
            foundContracts.addAll(wallet.getContracts());

            reporter.message("found wallet: " + wallet.toString());
            reporter.verbose("");

            HashMap<String, WalletValueModel> balance = new HashMap<>();
            String currencyKey;
            for (Contract contract : wallet.getContracts()) {
                try {
                    Decimal numcoins = new Decimal(contract.getStateData().getStringOrThrow(AMOUNT_FIELD_NAME));
                    currencyKey = contract.getOrigin().toBase64String();
                    WalletValueModel walletValueModel = balance.getOrDefault(currencyKey, new WalletValueModel());
                    walletValueModel.value = walletValueModel.value.add(numcoins);
                    String currencyTag = contract.getDefinition().getData().getString("currency_code", null);
                    if (currencyTag == null)
                        currencyTag = contract.getDefinition().getData().getString("unit_short_name", null);
                    if (currencyTag == null)
                        currencyTag = contract.getDefinition().getData().getString("unit_name", null);
                    if (currencyTag == null)
                        currencyTag = contract.getOrigin().toString();
                    walletValueModel.currency = currencyTag;
                    balance.put(currencyKey, walletValueModel);
                    reporter.verbose("found coins: " + contract.getOrigin().toString() + " -> " + numcoins + " (" + currencyTag + ") ");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            reporter.verbose("");
            reporter.message("total in the wallet: ");
            for (String c : balance.keySet()) {
                WalletValueModel w = balance.get(c);
                reporter.message(w.value + " (" + w.currency + ") ");
            }
        }
    }