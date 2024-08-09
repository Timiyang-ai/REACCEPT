@Test
  public void testRandomAttributes() throws Exception {
    Random rng = RandomUtils.getRandom();
    int nbAttributes = rng.nextInt(100) + 1;
    boolean[] selected = new boolean[nbAttributes];

    for (int nloop = 0; nloop < 100; nloop++) {
      Arrays.fill(selected, false);

      // randomly select some attributes
      int nbSelected = rng.nextInt(nbAttributes - 1);
      for (int index = 0; index < nbSelected; index++) {
        int attr;
        do {
          attr = rng.nextInt(nbAttributes);
        } while (selected[attr]);

        selected[attr] = true;
      }

      int m = rng.nextInt(nbAttributes);

      int[] attrs = DefaultTreeBuilder.randomAttributes(rng, selected, m);

      assertNotNull(attrs);
      assertEquals(Math.min(m, nbAttributes - nbSelected), attrs.length);

      for (int attr : attrs) {
        // the attribute should not be already selected
        assertFalse("an attribute has already been selected", selected[attr]);

        // each attribute should be in the range [0, nbAttributes[
        assertTrue(attr >= 0);
        assertTrue(attr < nbAttributes);

        // each attribute should appear only once
        assertEquals(ArrayUtils.indexOf(attrs, attr), ArrayUtils.lastIndexOf(attrs, attr));
      }
    }
  }