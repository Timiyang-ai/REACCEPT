void sendChallengeInitiateNegotiate(final ServletResponse response) {
        this.sendChallenge(NegotiateAuthenticationFilter.PROTOCOLS, response, null);
    }