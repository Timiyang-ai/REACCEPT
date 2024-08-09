    @Test
    public void getLastWord() {
        String lastWord = HintProvider.getLastWord("FROM P", "FROM P".length() - 1);
        assertEquals("P", lastWord);

        lastWord = HintProvider.getLastWord("FROM P", "FROM ".length() - 1);
        assertEquals("", lastWord);

        lastWord = HintProvider.getLastWord("SELECT p.team.na", "SELECT p.team.na".length() - 1);
        assertEquals("p.team.na", lastWord);

        lastWord = HintProvider.getLastWord("in(p.teams", "in(p.teams".length() - 1);
        assertEquals("p.teams", lastWord);

        lastWord = HintProvider.getLastWord("SELECT\t\tp.team.na", "SELECT\t\tp.team.na".length() - 1);
        assertEquals("p.team.na", lastWord);

        lastWord = HintProvider.getLastWord("SELECT\n\np.team.na", "SELECT\n\np.team.na".length() - 1);
        assertEquals("p.team.na", lastWord);

        lastWord = HintProvider.getLastWord("SELECT\r\np.team.na", "SELECT\r\np.team.na".length() - 1);
        assertEquals("p.team.na", lastWord);
    }