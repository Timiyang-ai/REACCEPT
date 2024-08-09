@Test
    public void testSendChallengeInitiateNegotiate() {

        this.out = new byte[1];
        this.out[0] = -1;

        this.negAuthFilter.sendChallengeInitiateNegotiate(this.response);

        Assertions.assertEquals("Negotiate", this.response.headersAdded.get("WWW-Authenticate").get(0));
        Assertions.assertEquals("NTLM", this.response.headersAdded.get("WWW-Authenticate").get(1));

        Assertions.assertEquals("keep-alive", this.response.headers.get("Connection"));

        Assertions.assertEquals(HttpServletResponse.SC_UNAUTHORIZED, this.response.sc);
        Assertions.assertEquals(0, this.response.errorCode);

        Assertions.assertFalse(this.response.isFlushed);
    }