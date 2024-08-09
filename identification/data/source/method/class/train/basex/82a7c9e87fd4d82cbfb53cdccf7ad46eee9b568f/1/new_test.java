@Test
  public void user() throws IOException {
    run("-cexit", "-cdrop user " + NAME);
    equals("5", new String[] { "-U" + NAME, "-P" + NAME, "-q5" },
        new String[] { "-ccreate user " + NAME + ' ' + NAME });
    run("-cexit", "-cdrop user " + NAME);
  }