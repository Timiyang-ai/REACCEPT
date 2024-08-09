@Test
    public void testGetSigningKey_nullConfig() {
        try {
            Key result = consumerUtil.getSigningKey((JwtConsumerConfig) null, (JwtContext) null, null);
            assertNull("Result was not null when it should have been. Result: " + result, result);
        } catch (Throwable t) {
            outputMgr.failWithThrowable(testName.getMethodName(), t);
        }
    }