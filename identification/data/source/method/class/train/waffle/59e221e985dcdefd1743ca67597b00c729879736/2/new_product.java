void sendChallengeInitiateNegotiate(final ServletResponse response) throws IOException {
        this.sendChallenge(NegotiateAuthenticationFilter.PROTOCOLS, response, null);
    }