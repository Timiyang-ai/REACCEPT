protected static int[] randomAttributes(Random rng, boolean[] selected, int m) {
    int nbNonSelected = 0; // number of non selected attributes
    for (boolean sel : selected) {
      if (!sel) {
        nbNonSelected++;
      }
    }
    
    if (nbNonSelected == 0) {
      log.warn("All attributes are selected !");
      return null;
    }
    
    int[] result;
    if (nbNonSelected <= m) {
      // return all non selected attributes
      result = new int[nbNonSelected];
      int index = 0;
      for (int attr = 0; attr < selected.length; attr++) {
        if (!selected[attr]) {
          result[index++] = attr;
        }
      }
    } else {
      result = new int[m];
      for (int index = 0; index < m; index++) {
        // randomly choose a "non selected" attribute
        int rind;
        do {
          rind = rng.nextInt(selected.length);
        } while (selected[rind]);
        
        result[index] = rind;
        selected[rind] = true; // temporarily set the chosen attribute to be selected
      }
      
      // the chosen attributes are not yet selected
      for (int attr : result) {
        selected[attr] = false;
      }
    }
    
    return result;
  }