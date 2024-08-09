  @Test
  public void testHeading() throws Exception {
    Sprite sprite = TestSprite.createTestSprite(canvasMock, handlerMock);

    final int degrees[] = { 0, 45, 90, 180, 270 };
    final double radians[] = { 0, Math.PI / 4, Math.PI / 2, Math.PI, Math.PI * 1.5 };
    final double cosine[] = { 1, 1 / Math.sqrt(2), 0, -1, 0 };
    final double sine[] = { 0, 1 / Math.sqrt(2), 1, 0, -1 };

    for (int i = 0; i < degrees.length; i++) {
      // Do a reality check on my data.
      assertEquals(degrees[i], (int) Math.toDegrees(radians[i]));
      assertEquals(cosine[i], Math.cos(radians[i]), DELTA);
      assertEquals(sine[i], Math.sin(radians[i]), DELTA);

      // Test Sprite
      sprite.Heading(-degrees[i]);  // Flip, because y increases in the downward direction
      assertEquals(-degrees[i], (int) sprite.userHeading);
      assertEquals(degrees[i], (int) sprite.heading);
      assertEquals(radians[i], normalizeRadians(sprite.headingRadians), DELTA);
      assertEquals(cosine[i], sprite.headingCos, DELTA);
      assertEquals(sine[i], sprite.headingSin, DELTA);
    }
  }