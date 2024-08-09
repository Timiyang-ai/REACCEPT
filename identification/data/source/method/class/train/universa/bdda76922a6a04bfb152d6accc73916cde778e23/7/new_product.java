private static void checkContract(Contract contract) {
        // First, check the sealed state
        if( !contract.isOk() ) {
            reporter.message("The capsule is not sealed properly:");
            contract.getErrors().forEach(e->reporter.error(e.getError().toString(), e.getObjectName(), e.getMessage()));
        }
        if( options.has("verbose")) {
            Set<PublicKey> keys = contract.getSealedByKeys();
            if (keys.size() > 0) {
                report("\nSignature contains " + keys.size() + " valid key(s):\n");
                keys.forEach(k -> {
                    KeyInfo i = k.info();
                    report("\t✔︎ " + i.getAlgorythm() + ":" + i.getKeyLength() * 8 + ":" + i.getBase64Tag());
                });
                report("\nWhich can play roles:\n");
                contract.getRoles().forEach((name, role) -> {
                    String canPlay = role.isAllowedForKeys(keys) ? "✔" : "✘";
                    report("\t " + canPlay + " " + role.getName());
                });


                report("\n");
            }
        }
        contract.seal();
        contract.check();
        contract.getErrors().forEach(error -> {
            addError(error.getError().toString(), error.getObjectName(), error.getMessage());
        });
        if(contract.getErrors().size() == 0) {
            report("Contract is valid");
        }
    }