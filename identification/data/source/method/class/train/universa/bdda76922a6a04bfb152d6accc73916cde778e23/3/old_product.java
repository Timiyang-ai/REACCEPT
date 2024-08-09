private static void checkContract(Contract contract) {
        // First, check the sealed state
        if (!contract.isOk()) {
            reporter.message("The capsule is not sealed properly:");
            contract.getErrors().forEach(e -> reporter.error(e.getError().toString(), e.getObjectName(), e.getMessage()));
        }
        Yaml yaml = new Yaml();
        if (reporter.isVerboseMode()) {

            report("api level:   "+contract.getApiLevel());
            report("contract id: " + contract.getId().toBase64String());
            report("issued:      " + contract.getIssuedAt());
            report("revision:    " + contract.getRevision());
            report("created:     " + contract.getCreatedAt());
            report("expires:     " + contract.getExpiresAt());

            System.out.println();

            contract.getRevokingItems().forEach(r-> {
                try {
                    ClientNetwork n = getClientNetwork();
                    System.out.println();
                    report("revoking item exists: "+r.getId().toBase64String());
                    report("\tstate: "+n.check(r.getId()));
                } catch (Exception clientError) {
                    clientError.printStackTrace();
                }
            });

            contract.getNewItems().forEach(n -> {
                System.out.println();
                report("New item exists:      "+n.getId().toBase64String());
                Contract nc = (Contract) n;
                boolean m  = nc.getOrigin().equals(contract.getOrigin());
                report("\tOrigin: "+((Contract) n).getOrigin());
                report("\t"+(m?"matches main contract origin" : "does not match main contract origin"));
            });

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
                    Binder x = DefaultBiMapper.serialize(perm.getParams());
                    BufferedReader br = new BufferedReader(
                            new StringReader(yaml.dumpAsMap(x)
                            ));
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
//        contract.seal();
        contract.check();
        addErrors(contract.getErrors());
        if (contract.getErrors().size() == 0) {
            report("Contract is valid");
        }
    }