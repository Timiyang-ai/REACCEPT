public boolean parse(final byte[] query) {
    final int[] q = cps(query);
    wc = new int[q.length];
    min = new int[q.length];
    max = new int[q.length];
    size = 0;

    final int ql = q.length;
    for(int qi = 0; qi < ql;) {
      int n = 1;
      int m = 1;
      // parse wildcards
      if(q[qi] == '.') {
        int c = ++qi < ql ? q[qi] : 0;
        // minimum/maximum number of occurrence
        if(c == '?') { // .?
          ++qi;
          n = 0;
          m = 1;
        } else if(c == '*') { // .*
          ++qi;
          n = 0;
          m = Integer.MAX_VALUE;
        } else if(c == '+') { // .+
          ++qi;
          n = 1;
          m = Integer.MAX_VALUE;
        } else if(c == '{') { // .{m,n}
          n = 0;
          m = 0;
          boolean f = false;
          while(true) {
            c = ++qi < ql ? q[qi] : 0;
            if(digit(c)) n = (n << 3) + (n << 1) + c - '0';
            else if(f && c == ',') break;
            else return false;
            f = true;
          }
          f = false;
          while(true) {
            c = ++qi < ql ? q[qi] : 0;
            if(digit(c)) m = (m << 3) + (m << 1) + c - '0';
            else if(f && c == '}') break;
            else return false;
            f = true;
          }
          ++qi;
          if(n > m) return false;
        }
        wc[size] = DOT;
      } else {
        if(q[qi] == '\\' && ++qi == ql) return false;
        wc[size] = q[qi++];
      }
      min[size] = n;
      max[size] = m;
      size++;
    }
    return true;
  }