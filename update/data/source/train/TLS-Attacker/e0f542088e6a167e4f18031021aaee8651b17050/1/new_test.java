@Test
    public void testReadPoints() throws Exception {
        List<ICEPoint> result = ICEPointReader.readPoints(NamedGroup.SECP192R1);
        assertEquals(5, result.get(0).getOrder());
    }