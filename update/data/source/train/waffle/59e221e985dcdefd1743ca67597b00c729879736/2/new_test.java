@Test
    public void testSendChallengeDuringNegotiate() throws IOException {

        final String myProtocol = "myProtocol";

        this.out = new byte[1];
        this.out[0] = -1;

        this.negAuthFilter.sendChallengeDuringNegotiate(myProtocol, this.response, this.out);

        Assertions.assertEquals(String.join(" ", myProtocol, Base64.getEncoder().encodeToString(this.out)),
                this.response.headers.get("WWW-Authenticate"));

        Assertions.assertEquals("keep-alive", this.response.headers.get("Connection"));

        Assertions.assertEquals(HttpServletResponse.SC_UNAUTHORIZED, this.response.errorCode);

        Assertions.assertFalse(this.response.isFlushed);
    }