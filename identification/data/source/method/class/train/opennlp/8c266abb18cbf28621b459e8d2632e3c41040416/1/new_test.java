@Test
  public void testEquals() {
    Span a1 = new Span(100, 1000, "test");
    Span a2 = new Span(100, 1000, "test");

    assertTrue(a1.equals(a2));
    
    // end is different
    Span b1 = new Span(100, 100, "test");
    assertFalse(a1.equals(b1));
    
    // type is different
    Span c1 = new Span(100, 1000, "Test");
    assertFalse(a1.equals(c1));
    
    Span d1 = new Span(100, 1000);
    
    assertFalse(d1.equals(a1));
    assertFalse(a1.equals(d1));
    
  }