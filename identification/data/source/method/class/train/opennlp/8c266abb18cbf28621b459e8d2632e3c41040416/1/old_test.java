@Test
  public void testEquals() {
    Span a1 = new Span(100, 1000, "test");
    Span a2 = new Span(100, 1000, "test");

    assertTrue(a1.equals(a2));
    
    Span b1 = new Span(100, 100, "test");
    assertFalse(a1.equals(b1));
    
    Span c1 = new Span(100, 1000, "Test");
    assertFalse(a1.equals(c1));
    
  }