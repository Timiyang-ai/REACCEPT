  @Test
  public void reverseTest() {
    assertNull(reverse(null), null);
    assertEquals(reverse(""), "");
    assertEquals(reverse("a"), "a");
    assertEquals(reverse("ab"), "ba");
    assertEquals(reverse("ab cd "), " dc ba");
  }