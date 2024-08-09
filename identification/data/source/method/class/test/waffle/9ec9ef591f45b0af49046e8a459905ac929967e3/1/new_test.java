@Test
    public void testIsSPNegTokenInitMessage() {
        final SimpleHttpRequest request = new SimpleHttpRequest();
        final AuthorizationHeader header = new AuthorizationHeader(request);
        Assert.assertFalse(header.isSPNegTokenInitMessage());
        request.addHeader("Authorization", "");
        Assert.assertFalse(header.isSPNegTokenInitMessage());
        request.addHeader(
                "Authorization",
                "Negotiate YHYGBisGAQUFAqBsMGqgMDAuBgorBgEEAYI3AgIKBgkqhkiC9xIBAgIGCSqGSIb3EgECAgYKKwYBBAGCNwICHqI2BDROVExNU1NQAAEAAACXsgjiAwADADEAAAAJAAkAKAAAAAYBsR0AAAAPR0xZQ0VSSU5FU0FE");
        Assert.assertTrue(header.isSPNegTokenInitMessage());
    }