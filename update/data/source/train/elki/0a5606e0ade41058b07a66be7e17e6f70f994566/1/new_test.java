@Test
  public void testEquals() {
    // equals(Matrix)
    final double[] v1 = {2,4,3,0,-5,9};
    
    // make copy of by hand to be independent of copy 
    // module to be able to use Equals in testcopy
    final double[] v1_copy = {2,4,3,0,-5,9};
    
    assertThat(v1, is(equalTo(v1)));
    assertThat(v1, is(equalTo(v1_copy)));
    assertThat(unitVector(6, 2), is(not(equalTo(v1))));
    
    
    // Equals(Matrix)
    final double[][] m1 = {{ 1, 2, 3},
                           { 7, 3, 9},
                           { 0, 2, 1},
                           {20,-5, 4},
                           {-3, 0, 3},
                           { 1, 1, 1}};
    
    // make copy of by hand to be independent of copy 
    // module to be able to use Equals in testcopy
    final double[][] m1_copy = {{ 1, 2, 3},
                                { 7, 3, 9},
                                { 0, 2, 1},
                                {20,-5, 4},
                                {-3, 0, 3},
                                { 1, 1, 1}};
        
    assertThat(m1, is(equalTo(m1)));
    assertThat(m1, is(equalTo(m1_copy)));
    assertThat(identity(6, 3), is(not(equalTo(m1))));
    
    // TODO: Question: more testcases?
    
  }