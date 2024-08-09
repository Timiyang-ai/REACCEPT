@Test
  public void testMakeAppSecretProof() {
    System.out.println("Testing make App Secret Proof");
    DefaultFacebookClient facebookClient = new DefaultFacebookClient("test", "test");
    String test = facebookClient.makeAppSecretProof();
    // obtained from running hash_hmac("sha256","test","test");
    String php_result = "88cd2108b5347d973cf39cdf9053d7dd42704876d8c9a9bd8e2d168259d3ddf7";
    assertEquals(php_result, test);
    // obtained from running hash_hmac("sha256","helloWorld",'PRIE7$oG2uS-Yf17kEnUEpi5hvW/#AFo');
    String php_result2 = "cb064987988fcd658470d6a24f1c68f6d7982c80ab9efb08cb8c84ef88fd03e1";
    DefaultFacebookClient facebookClient2 = new DefaultFacebookClient("helloWorld", "PRIE7$oG2uS-Yf17kEnUEpi5hvW/#AFo");
    String test2 = facebookClient2.makeAppSecretProof();
    assertEquals(php_result2, test2);
  }