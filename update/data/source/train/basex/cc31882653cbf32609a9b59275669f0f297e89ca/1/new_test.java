@Test public void soundexDifference() {
    soundexDiff("Smith", "Smythe", 4);
    soundexDiff("Ann", "Andrew", 2);
    soundexDiff("Margaret", "Andrew", 1);
    soundexDiff("Janet", "Margaret", 0);

    soundexDiff("Green", "Greene", 4);
    soundexDiff("Blotchet-Halls", "Greene", 0);

    soundexDiff("Smith", "Smythe", 4);
    soundexDiff("Smithers", "Smythers", 4);
    soundexDiff("Anothers", "Brothers", 2);
  }