    @Test
    public void encodeCookie() {
        // Test with no groups
        String actual = service.encodeCookie(new String[]{"user"});
        String expected = "eyJhbGciOiJIUzI1NiIsImtpZCI6IkhNQUMifQ.eyJleHAiOjE0NjMxNTE5MDAsInN1YiI6InVzZXIiLCJwcmluY2lwYWxzIjpbXX0.q76UatxiKI95uDZtCL1Oc48dBjXfhSgb1SpBkMAjP_E";
        Assert.assertEquals(expected, actual);

        // Test with one group
        actual = service.encodeCookie(new String[]{"dladmin", groupPrincipalsJson("admin")});
        expected = "eyJhbGciOiJIUzI1NiIsImtpZCI6IkhNQUMifQ.eyJleHAiOjE0NjMxNTE5MDAsInN1YiI6ImRsYWRtaW4iLCJwcmluY2lwYWxzIjpbIntcImNvbS50aGlua2J"
                        + "pZ2FuYWx5dGljcy5zZWN1cml0eS5Hcm91cFByaW5jaXBhbFwiOltcImFkbWluXCJdfSJdfQ.DH4pxE8eWCmqPlhFMiEAbBja5k833gg0guE6m8DXvIA";
        Assert.assertEquals(expected, actual);

        // Test with multiple groups
        actual = service.encodeCookie(new String[]{"dladmin", groupPrincipalsJson("designers", "operators")});
        expected = "eyJhbGciOiJIUzI1NiIsImtpZCI6IkhNQUMifQ.eyJleHAiOjE0NjMxNTE5MDAsInN1YiI6ImRsYWRtaW4iLCJwcmluY2lwYWxzIjpbIntcImNvbS50aGlua2J"
                        + "pZ2FuYWx5dGljcy5zZWN1cml0eS5Hcm91cFByaW5jaXBhbFwiOltcImRlc2lnbmVyc1wiLFwib3BlcmF0b3JzXCJdfSJdfQ.kESqgybFd5uyOn1Mjy5dUgwjE24-MstYZjysXS58G8s";
        Assert.assertEquals(expected, actual);
    }