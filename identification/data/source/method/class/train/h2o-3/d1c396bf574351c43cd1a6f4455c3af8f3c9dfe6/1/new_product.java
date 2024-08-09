public Vec adaptTo( String[] domain ) {
    if(!isBad() && isNumeric() && !ArrayUtils.isInt(domain)) { // try to adapt double domain
      // treat double domain here - return full vector copy instead of mapping double to ints on the fly in a WrappedVec
      final int oldDomainLen = domain.length;
      int nan_cnt = 0;
        int j = 0;
        double [] double_domain = MemoryManager.malloc8d(domain.length);
        for (int i = 0; i < double_domain.length; ++i)
        try {
          double_domain[j] = Double.parseDouble(domain[i]);
          j++;
        } catch(NumberFormatException ex){nan_cnt++;}
        if (j < double_domain.length)
          double_domain = Arrays.copyOf(double_domain, j);
        double[] new_double_domain = new VecUtils.CollectDoubleDomain(double_domain, 100000).doAll(this).domain();
        if (new_double_domain.length > 0) {
          int n = domain.length;
          domain = Arrays.copyOf(domain, domain.length + new_double_domain.length);
          for (int i = 0; i < new_double_domain.length; ++i)
            domain[n + i] = String.valueOf(new_double_domain[i]);
        }
        Vec res = makeZero(domain);
        double_domain = MemoryManager.malloc8d(domain.length - nan_cnt);
        j = 0;
        final int[] indeces = MemoryManager.malloc4(domain.length - nan_cnt);
        for (int i = 0; i < domain.length; ++i) {
          try {
            double_domain[j] = Double.parseDouble(domain[i]);
            indeces[j] = i;
            j++;
          } catch (NumberFormatException ex) {/*ignore*/}
        }
        if (!ArrayUtils.isSorted(double_domain))
          ArrayUtils.sort(indeces, double_domain);
        final double[] sorted_domain_vals = ArrayUtils.select(double_domain, indeces);
        new MRTask() {
          @Override
          public void map(Chunk c0, Chunk c1) {
            for (int i = 0; i < c0._len; ++i) {
              double d = c0.atd(i);
              if (Double.isNaN(d))
                c1.setNA(i);
              else {
                c1.set(i, indeces[Arrays.binarySearch(sorted_domain_vals, d)]);
              }
            }
          }
        }.doAll(new Vec[]{this, res});
        return res;
    }
    return new CategoricalWrappedVec(group().addVec(),_rowLayout,domain,this._key);
  }