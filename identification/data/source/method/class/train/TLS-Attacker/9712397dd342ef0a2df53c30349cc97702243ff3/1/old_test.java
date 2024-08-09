@Test
    public void testGeneratePlainPkcs1Vectors() {
        List<Pkcs1Vector> vectors = Pkcs1VectorGenerator.generatePlainPkcs1Vectors(2048,
                BleichenbacherCommandConfig.Type.FAST, ProtocolVersion.TLS12);
        Assert.assertNotNull(vectors);
        Assert.assertEquals("11 PKCS#1 vectors should be generated", 11, vectors.size());
    }