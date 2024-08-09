void sendChallengeDuringNegotiate(final String protocol, final ServletResponse response, final byte[] out) {
        final List<String> protocolsList = new ArrayList<>();
        protocolsList.add(protocol);
        this.sendChallenge(protocolsList, response, out);
    }