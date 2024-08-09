private static void printWallets(List<Wallet> wallets) {
        reporter.message("");

        List<Contract> foundContracts = new ArrayList<>();
        for (Wallet wallet : wallets) {
            foundContracts.addAll(wallet.getContracts());

            reporter.message("found wallet: " + wallet.toString());
            reporter.verbose("");

            HashMap<String, Integer> balance = new HashMap<String, Integer>();
            Integer numcoins;
            String currency;
            for (Contract contract : wallet.getContracts()) {
                try {
                    numcoins = contract.getStateData().getIntOrThrow(AMOUNT_FIELD_NAME);
                    currency = contract.getDefinition().getData().getOrThrow("currency_code");
                    if (balance.containsKey(currency)) {
                        balance.replace(currency, balance.get(currency) + numcoins);
                    } else {
                        balance.put(currency, numcoins);
                    }
                    reporter.verbose("found coins: " +
                                             contract.getDefinition().getData().getOrThrow("name") +
                                             " -> " + numcoins + " (" + currency + ") ");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            reporter.verbose("");
            reporter.message("total in the wallet: ");
            for (String c : balance.keySet()) {
                reporter.message(balance.get(c) + " (" + c + ") ");
            }
        }
    }