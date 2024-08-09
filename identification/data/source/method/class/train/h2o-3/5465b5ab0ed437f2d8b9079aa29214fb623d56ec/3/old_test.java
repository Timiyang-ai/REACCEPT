  static void exec_str( String str, Session ses ) {
    Val val = Rapids.exec(str,ses);
    switch( val.type() ) {
    case Val.FRM:
      Frame fr = val.getFrame();
      System.out.println(fr);
      checkSaneFrame();
      break;
    case Val.NUM:
      System.out.println("num= "+val.getNum());
      checkSaneFrame();
      break;
    case Val.STR:
      System.out.println("str= "+val.getStr());
      checkSaneFrame();
      break;
    default:
      throw water.H2O.fail();
    }
  }