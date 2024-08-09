@Test
    public void testReadPoints() throws Exception {
        String namedCurve = "secp192r1";
        List<ICEPoint> result = ICEPointReader.readPoints(namedCurve);

        assertEquals(5, result.get(0).getOrder());
    }