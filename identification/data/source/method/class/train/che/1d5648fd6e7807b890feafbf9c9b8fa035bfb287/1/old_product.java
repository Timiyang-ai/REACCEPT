@Override
    public Set<Macro> getMacros(MachineImpl devMachine) {
        final Set<Macro> macros = Sets.newHashSet();

        for (Map.Entry<String, ServerImpl> entry : devMachine.getServers().entrySet()) {

            if (!entry.getValue().getUrl().contains(":")) {
                continue;
            }

            final String externalPort = entry.getValue().getUrl().split(":")[1];

            Macro macro = new BaseMacro(KEY.replace("%", entry.getKey()),
                                        externalPort,
                                        "Returns port of a server registered by name");

            macros.add(macro);

            // register port without "/tcp" suffix
            if (entry.getKey().endsWith("/tcp")) {
                final String port = entry.getKey().substring(0, entry.getKey().length() - 4);

                Macro shortMacro = new BaseMacro(KEY.replace("%", port),
                                                 externalPort,
                                                 "Returns port of a server registered by name");

                macros.add(shortMacro);
            }
        }

        return macros;
    }