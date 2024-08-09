@Test public void soundexVariations() {
    soundexVariations("B650",
      "BARHAM", "BARONE", "BARRON", "BERNA", "BIRNEY", "BIRNIE", "BOOROM", "BOREN", "BORN", "BOURN",
      "BOURNE", "BOWRON", "BRAIN", "BRAME", "BRANN", "BRAUN", "BREEN", "BRIEN", "BRIM", "BRIMM",
      "BRINN", "BRION", "BROOM", "BROOME", "BROWN", "BROWNE", "BRUEN", "BRUHN", "BRUIN", "BRUMM",
      "BRUN", "BRUNO", "BRYAN", "BURIAN", "BURN", "BURNEY", "BYRAM", "BYRNE", "BYRON", "BYRUM"
    );
    soundexVariations("O165",
      "OBrien", "'OBrien", "O'Brien", "OB'rien", "OBr'ien", "OBri'en", "OBrie'n", "OBrien'"
    );
    soundexVariations("K525",
      "KINGSMITH", "-KINGSMITH", "K-INGSMITH", "KI-NGSMITH", "KIN-GSMITH", "KING-SMITH",
      "KINGS-MITH", "KINGSM-ITH", "KINGSMI-TH", "KINGSMIT-H", "KINGSMITH-"
    );
    soundexVariations("W452",
      "Williams"
    );
    soundexVariations("S460",
      "Sgler", "Swhgler",
      "SAILOR", "SALYER", "SAYLOR", "SCHALLER", "SCHELLER", "SCHILLER", "SCHOOLER", "SCHULER",
      "SCHUYLER", "SEILER", "SEYLER", "SHOLAR", "SHULER", "SILAR", "SILER", "SILLER"
    );
    soundexVariations("E625",
      "Erickson", "Erickson", "Erikson", "Ericson", "Ericksen", "Ericsen"
    );
  }