public boolean removeWatchedAddresses(final List<LegacyAddress> addresses) {
        List<Script> scripts = Lists.newArrayList();

        for (LegacyAddress address : addresses) {
            Script script = ScriptBuilder.createOutputScript(address);
            scripts.add(script);
        }

        return removeWatchedScripts(scripts);
    }