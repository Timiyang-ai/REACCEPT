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
    String php_result3 = "75d686df7a1e937b61b9d062c5fbad9a933fe70c15be0fec96dcb158fdada663";
    DefaultFacebookClient facebookClient3 =
        new DefaultFacebookClient(
          "CAAAAMXGpQQgBACydRTLJiIG7qh0J9pWvzlZAXZCI4XZAzfuAdzadxVekKiO6dypZBoq8OdZA2pRgKXcAGnu1ht0vZCsswxsGscroRMIaZCtLMN5lRbyZAmxM59vdZB2oZBfggMP576SOJSCZAVo3Kd4FFerfYKl7X7mZCW0jMmquwM6wElZCEfcF2R0Yd",
          "e17b62bbbf5e1c5f5a45e10efcc31fa6");
    String test3 = facebookClient3.makeAppSecretProof();
    System.out.println(test3);
    assertEquals(php_result3, test3);
  }