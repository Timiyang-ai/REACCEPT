@Test
    public void decodeCookie() {
        // Test with no groups
        String[] actual = service.decodeCookie("eyJhbGciOiJIUzI1NiIsImtpZCI6IkhNQUMifQ.eyJleHAiOjE0NjMxNTE5MDAsInN1YiI6InVzZXIiLCJwcmluY2lwYWxzIjpbXX0.q76UatxiKI95uDZtCL1Oc48dBjXfhSgb1SpBkMAjP_E");
        String[] expected = new String[]{"user"};
        Assert.assertArrayEquals(expected, actual);

        // Test with one group
        actual = service.decodeCookie("eyJhbGciOiJIUzI1NiIsImtpZCI6IkhNQUMifQ.eyJleHAiOjE0NjMxNTE5MDAsInN1YiI6ImRsYWRtaW4iLCJwcmluY2lwYWxzIjpbIntcImNvbS50aGlua2J"
                        + "pZ2FuYWx5dGljcy5zZWN1cml0eS5Hcm91cFByaW5jaXBhbFwiOltcImFkbWluXCJdfSJdfQ.DH4pxE8eWCmqPlhFMiEAbBja5k833gg0guE6m8DXvIA");
        expected = new String[]{"dladmin", groupPrincipalsJson("admin")};
        Assert.assertArrayEquals(expected, actual);

        // Test with multiple groups
        actual = service.decodeCookie("eyJhbGciOiJIUzI1NiIsImtpZCI6IkhNQUMifQ.eyJleHAiOjE0NjMxNTE5MDAsInN1YiI6ImRsYWRtaW4iLCJwcmluY2lwYWxzIjpbIntcImNvbS50aGlua2J"
                        + "pZ2FuYWx5dGljcy5zZWN1cml0eS5Hcm91cFByaW5jaXBhbFwiOltcImRlc2lnbmVyc1wiLFwib3BlcmF0b3JzXCJdfSJdfQ.kESqgybFd5uyOn1Mjy5dUgwjE24-MstYZjysXS58G8s");
        expected = new String[]{"dladmin", groupPrincipalsJson("designers", "operators")};
        Assert.assertArrayEquals(expected, actual);
    }