public boolean removeWatchedAddresses(final List<Address> addresses) {
        List<Script> scripts = new ArrayList<>();

        for (Address address : addresses) {
            Script script = ScriptBuilder.createOutputScript(address);
            scripts.add(script);
        }

        return removeWatchedScripts(scripts);
    }