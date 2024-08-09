@Test
public void testUniformCdf() {
    logger.info("uniformCdf");
    double x = 3.0;
    double a = 2.0;
    double b = 10.0;
    double expResult = 0.125; // Assuming the expected result remains the same as the logic might be similar
    double result = ContinuousDistributions.uniformCdf(x, a, b);
    assertEquals(expResult, result, TestConfiguration.DOUBLE_ACCURACY_HIGH);
}