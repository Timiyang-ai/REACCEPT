  @Test
  public void setWitnesses() {
    List<Witness> witnesses = new ArrayList<>();
    Witness witness = new Witness();
    witnesses.add(witness);
    genesisBlock.setWitnesses(witnesses);
    Assert.assertEquals(1, genesisBlock.getWitnesses().size());
  }