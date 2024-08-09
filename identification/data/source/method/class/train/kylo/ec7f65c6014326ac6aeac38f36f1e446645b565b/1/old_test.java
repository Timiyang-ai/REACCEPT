@Test
    public void decodeCookie() {
        // Test with no groups
        String[] actual = service.decodeCookie("eyJhbGciOiJIUzI1NiIsImtpZCI6IkhNQUMifQ.eyJleHAiOjE0NjMxNTE5MDAsInN1YiI6InVzZXIiLCJncm91cHMiOltdfQ.NKAD4m4bn1eDMGA9HSihOsLwrSUcDhBYfXIL7uJPWdo");
        String[] expected = new String[]{"user"};
        Assert.assertArrayEquals(expected, actual);

        // Test with one group
        actual = service.decodeCookie("eyJhbGciOiJIUzI1NiIsImtpZCI6IkhNQUMifQ.eyJleHAiOjE0NjMxNTE5MDAsInN1YiI6ImRsYWRtaW4iLCJncm91cHMiOlsiYWRtaW4iXX0."
                                      + "3RX9hsmfNA1rtWMs309N5MV5_gd4FUtU_odFIIOqsoY");
        expected = new String[]{"dladmin", "admin"};
        Assert.assertArrayEquals(expected, actual);

        // Test with multiple groups
        actual = service.decodeCookie("eyJhbGciOiJIUzI1NiIsImtpZCI6IkhNQUMifQ.eyJleHAiOjE0NjMxNTE5MDAsInN1YiI6ImRsYWRtaW4iLCJncm91cHMiOlsiZGVzaWduZXJzIiwib3BlcmF0b3JzIl19."
                                      + "fRxn00QbHAjL-R0DI1DmYfLEi3F7eMb3V2vTvgcFOy8");
        expected = new String[]{"dladmin", "designers", "operators"};
        Assert.assertArrayEquals(expected, actual);
    }