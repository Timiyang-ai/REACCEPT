    public void test_toString() {
        int[] pos = { 0, 1, 1000, Integer.MAX_VALUE, (Integer.MAX_VALUE - 1) };
        SSLEngineResult.Status [] enS =
            SSLEngineResult.Status.values();
        SSLEngineResult.HandshakeStatus [] enHS =
            SSLEngineResult.HandshakeStatus.values();
        for (int i = 0; i < enS.length; i++) {
            for (int j = 0; j < enHS.length; j++) {
                for (int n = 0; n < pos.length; n++) {
                    for (int l = 0; l < pos.length; ++l) {
                        SSLEngineResult res = new SSLEngineResult(enS[i],
                                enHS[j], pos[n], pos[l]);
                        assertNotNull("Result of toSring() method is null",
                                res.toString());
                    }
                }
            }
        }
    }