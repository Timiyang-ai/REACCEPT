public static List<Integer> getSubListIndex(Object[] tofind, Object[] tokens){
     return getSubListIndex(tofind, tokens, (o1) -> o1.first().equals(o1.second()));
  }