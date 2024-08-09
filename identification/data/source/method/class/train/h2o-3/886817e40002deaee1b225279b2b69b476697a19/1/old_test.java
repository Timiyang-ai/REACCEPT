@Test public void testToEnum() {
    testToEnumDomainMatch(vec(0,1,0,1), ar("0", "1") );
    testToEnumDomainMatch(vec(1,2,3,4,5,6,7), ar("1", "2", "3", "4", "5", "6", "7") );
    testToEnumDomainMatch(vec(-1,0,1,2,3,4,5,6), ar("-1", "0", "1", "2", "3", "4", "5", "6") );
  }