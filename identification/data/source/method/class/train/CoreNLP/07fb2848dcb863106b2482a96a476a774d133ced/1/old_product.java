public static List<Integer> getSubListIndex(Object[] tofind, Object[] tokens, Function<Pair, Boolean> matchingFunction){
    if(tofind.length > tokens.length)
      return null;
    List<Integer> allIndices = new ArrayList<>();
    boolean matched = false;
    int index = -1;
    int lastUnmatchedIndex = 0;
    for(int i = 0 ; i < tokens.length;){
      for(int j = 0; j < tofind.length ;){
        if(matchingFunction.apply(new Pair(tofind[j], tokens[i]))){
          index = i;
          i++;
          j++;
          if(j == tofind.length)
          {
            matched = true;
            break;
          }
        }else{
          j = 0;
          i = lastUnmatchedIndex +1;
          lastUnmatchedIndex = i;
          index = -1;
          if(lastUnmatchedIndex == tokens.length)
            break;
        }
        if(i >= tokens.length){
          index = -1;
          break;
        }
      }
      if(i == tokens.length || matched){
        if(index >= 0)
          //index = index - l1.length + 1;
          allIndices.add(index - tofind.length + 1);
        matched = false;
        lastUnmatchedIndex = index;

        //break;
      }
    }
    //get starting point

    return allIndices;
  }