public static int[] getPredictions( int numK, float[] preds, double data[] ) {
    assert(numK <= preds.length-1);
    int[] labels = new int[numK];
    // create a sorted mapping from probability to label(s)
    TreeMap<Float, List<Integer> > prob_idx = new TreeMap<Float, List<Integer> >(new Comparator<Float>() {
      @Override
      public int compare(Float o1, Float o2) {
        if (o1 > o2) return -1;
        if (o2 > o1) return 1;
        return 0;
      }
    });
    for (int i = 1; i < preds.length; ++i) {
      final Float prob = preds[i];
      final int label = i-1;
      assert(prob >= 0 && prob <= 1) : "prob is not inside [0,1]: " + prob;
      if (prob_idx.containsKey(prob)) {
        prob_idx.get(prob).add(label); //add all ties
      } else {
        // add prob to top K probs only if either:
        // 1) don't have K probs yet
        // 2) prob is greater than the smallest prob in the store -> evict the smallest
        if (prob_idx.size() < numK || prob > prob_idx.lastKey()) {
          List<Integer> li = new LinkedList<Integer>();
          li.add(label);
          prob_idx.put(prob, li);
        }
        // keep size small, only need the best numK probabilities (max-heap)
        if (prob_idx.size()>numK) {
          prob_idx.remove(prob_idx.lastKey());
        }
      }
    }
    assert(!prob_idx.isEmpty());
    assert(prob_idx.size() <= numK); //have at most numK probabilities, maybe less if there are ties

    int i = 0; //which label we are filling in
    while (i < numK && !prob_idx.isEmpty()) {
      final Map.Entry p_id = prob_idx.firstEntry();
      final Float prob = (Float)p_id.getKey(); //max prob.
      final List<Integer> indices = (List<Integer>)p_id.getValue(); //potential candidate labels if there are ties
      if (i + indices.size() <= numK) for (Integer id : indices) labels[i++] = id;
      else {
        // Tie-breaking logic: pick numK-i classes (indices) from the list of indices.
        // if data == null, then pick the first numK-i indices, otherwise break ties pseudo-randomly.
        while (i<numK) {
          assert(!indices.isEmpty());
          long hash = 0;
          if( data != null )
            for( double d : data ) hash ^= Double.doubleToRawLongBits(d+i) >> 6; // drop 6 least significant bits of mantissa (layout of long is: 1b sign, 11b exp, 52b mantissa)
          labels[i++] = indices.remove((int)(Math.abs(hash)%indices.size()));
        }
        assert(i==numK);
      }
      prob_idx.remove(prob);
    }
    assert(i==numK);
    return labels;
  }