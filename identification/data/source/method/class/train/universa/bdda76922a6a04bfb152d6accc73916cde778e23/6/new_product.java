private static void checkContract(Contract contract) {
        // First, check the sealed state
        if (!contract.isOk()) {
            reporter.message("The capsule is not sealed properly:");
            contract.getErrors().forEach(e -> reporter.error(e.getError().toString(), e.getObjectName(), e.getMessage()));
        }
        Yaml yaml = new Yaml();
        if (reporter.isVerboseMode()) {

            report("api level:   " + contract.getApiLevel());
            report("contract id: " + contract.getId().toBase64String());
            report("issued:      " + contract.getIssuedAt());
            report("revision:    " + contract.getRevision());
            report("created:     " + contract.getCreatedAt());
            report("expires:     " + contract.getExpiresAt());

            System.out.println();
            Set<PublicKey> keys = contract.getSealedByKeys();

            contract.getRevoking().forEach(r -> {
                try {
                    ClientNetwork n = getClientNetwork();
                    System.out.println();
                    report("revoking item exists: " + r.getId().toBase64String());
                    report("\tstate: " + n.check(r.getId()));
                    HashId origin = r.getOrigin();
                    boolean m = origin.equals(contract.getOrigin());
                    report("\tOrigin: " + origin);
                    report("\t" + (m ? "matches main contract origin" : "does not match main contract origin"));
                    if( r.canBeRevoked(keys) ) {
                        report("\trevocation is allowed");
                    }
                    else
                        reporter.error(Errors.BAD_REVOKE.name(), r.getId().toString(), "revocation not allowed");
                } catch (Exception clientError) {
                    clientError.printStackTrace();
                }
            });

            contract.getNewItems().forEach(n -> {
                System.out.println();
                report("New item exists:      " + n.getId().toBase64String());
                Contract nc = (Contract) n;
                boolean m = nc.getOrigin().equals(contract.getOrigin());
                report("\tOrigin: " + ((Contract) n).getOrigin());
                report("\t" + (m ? "matches main contract origin" : "does not match main contract origin"));
            });

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

                reporter.newLine();
            }
        }

        Multimap<String, Permission> permissions = contract.getPermissions();
        Collection<Permission> sjs = permissions.get("split_join");
        if (sjs != null) {
            sjs.forEach(sj -> checkSj(contract, sj));
        }

        try {
            contract.check();
        } catch (Quantiser.QuantiserException e) {
            addError("QUANTIZER_COST_LIMIT", contract.toString(), e.getMessage());
        } catch (Exception e) {
            addError(Errors.FAILURE.name(), contract.toString(), e.getMessage());
        }
        addErrors(contract.getErrors());
        if (contract.getErrors().size() == 0) {
            report("Contract is valid");
        }
    }