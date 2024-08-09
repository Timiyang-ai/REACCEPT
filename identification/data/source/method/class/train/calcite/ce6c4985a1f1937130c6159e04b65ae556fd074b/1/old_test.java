@Test public void testDiffLines() {
    String[] before = {
      "Get a dose of her in jackboots and kilt",
      "She's killer-diller when she's dressed to the hilt",
      "She's the kind of a girl that makes The News of The World",
      "Yes you could say she was attractively built.",
      "Yeah yeah yeah."
    };
    String[] after = {
      "Get a dose of her in jackboots and kilt",
      "(they call her \"Polythene Pam\")",
      "She's killer-diller when she's dressed to the hilt",
      "She's the kind of a girl that makes The Sunday Times",
      "seem more interesting.",
      "Yes you could say she was attractively built."
    };
    String diff =
        DiffTestCase.diffLines(
            Arrays.asList(before),
            Arrays.asList(after));
    assertEquals(
        diff,
        TestUtil.fold(
            "1a2\n"
            + "> (they call her \"Polythene Pam\")\n"
            + "3c4,5\n"
            + "< She's the kind of a girl that makes The News of The World\n"
            + "---\n"
            + "> She's the kind of a girl that makes The Sunday Times\n"
            + "> seem more interesting.\n"
            + "5d6\n"
            + "< Yeah yeah yeah.\n"));
  }