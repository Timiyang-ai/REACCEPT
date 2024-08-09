void sendChallengeDuringNegotiate(final String protocol, final ServletResponse response, final byte[] out)
            throws IOException {
        final List<String> protocolsList = new ArrayList<>();
        protocolsList.add(protocol);
        this.sendChallenge(protocolsList, response, out);
    }