@Test
  public void soundexDifference() {
    soundexDifference("Smith", "Smythe", 4);
    soundexDifference("Ann", "Andrew", 2);
    soundexDifference("Margaret", "Andrew", 1);
    soundexDifference("Janet", "Margaret", 0);

    soundexDifference("Green", "Greene", 4);
    soundexDifference("Blotchet-Halls", "Greene", 0);

    soundexDifference("Smith", "Smythe", 4);
    soundexDifference("Smithers", "Smythers", 4);
    soundexDifference("Anothers", "Brothers", 2);
  }