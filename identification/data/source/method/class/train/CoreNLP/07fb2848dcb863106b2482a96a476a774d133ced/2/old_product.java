public static List<Integer> getSubListIndex(Object[] l1, Object[] l2){ 
    if(l1.length > l2.length)
      return null;
    List<Integer> allIndices = new ArrayList<Integer>();
    boolean matched = false;
    int index = -1;
    int lastUnmatchedIndex = 0;
    for(int i = 0 ; i < l2.length;){
      for(int j = 0; j < l1.length ;){
        if(l1[j].equals(l2[i])){
          index = i;
          i++;
          j++;
          if(j == l1.length)
          { 
            matched = true;
            break;
          }
        }else{
          j = 0;
          i = lastUnmatchedIndex +1;
          lastUnmatchedIndex = i;
          index = -1;
          if(lastUnmatchedIndex == l2.length)
            break;
        }
        if(i >= l2.length){
          index = -1;
          break;
        }
      }
      if(i == l2.length || matched){
        if(index >= 0)
          //index = index - l1.length + 1;
          allIndices.add(index - l1.length + 1);
        matched = false;
        lastUnmatchedIndex = index;
        
        //break;
      }
    }
    //get starting point
    
    return allIndices;
  }