private static void checkContract(Contract contract) {
        // First, check the sealed state
        if (!contract.isOk()) {
            reporter.message("The capsule is not sealed properly:");
            contract.getErrors().forEach(e -> reporter.error(e.getError().toString(), e.getObjectName(), e.getMessage()));
        }
        Yaml yaml = new Yaml();
        if (options.has("verbose")) {

            report("contract id: "+ contract.getId().toBase64String());
            report("issued:   "+contract.getIssuedAt());
            report("revision: "+contract.getRevision());
            report("created:  "+contract.getCreatedAt());
            report("expires:  "+contract.getExpiresAt());

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
                    report("\t" + canPlay + " " + role.getName());
                });

                report("\nAnd have permissions:\n");
                contract.getPermissions().values().forEach(perm -> {
                    String canPlay = perm.isAllowedForKeys(keys) ? "✔" : "✘";
                    report("\t" + canPlay + " " + perm.getName());
                    BufferedReader br = new BufferedReader(new StringReader(yaml.dumpAsMap(perm.getParams())));
                    try {
                        for (String line; (line = br.readLine()) != null; ) {
                            report("\t    " + line);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                report("\n");
            }
        }
        contract.seal();
        contract.check();
        addErrors(contract.getErrors());
        if (contract.getErrors().size() == 0) {
            report("Contract is valid");
        }
    }