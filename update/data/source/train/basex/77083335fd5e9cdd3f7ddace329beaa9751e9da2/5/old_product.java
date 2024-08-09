final int node(final Data data, final int pre, final FTPosData ft)
      throws IOException {

    final TokenList nsp = data.ns.size() != 0 ? new TokenList() : null;
    final int[] parStack = new int[IO.MAXHEIGHT];
    final byte[][] names = new byte[IO.MAXHEIGHT][];
    names[0] = ns(EMPTY);

    int l = 0;
    int p = pre;

    // loop through all table entries
    final int s = pre + data.size(pre, data.kind(p));
    while(p < s && !finished()) {
      final int k = data.kind(p);
      final int r = data.parent(p, k);

      // close opened elements...
      while(l > 0 && parStack[l - 1] >= r) {
        closeElement();
        --l;
      }

      if(k == Data.DOC) {
        ++p;
      } else if(k == Data.TEXT) {
        final FTPos ftd = ft != null ? ft.get(p) : null;
        if(ftd != null) text(data.text(p++, true), ftd);
        else text(data.text(p++, true));
      } else if(k == Data.COMM) {
        comment(data.text(p++, true));
      } else if(k == Data.ATTR) {
        attribute(data.name(p, k), data.text(p++, false));
      } else if(k == Data.PI) {
        pi(data.name(p, k), data.atom(p++));
      } else {
        // add element node
        final byte[] name = data.name(p, k);
        openElement(name);

        // add namespace definitions
        byte[] empty = names[l];
        if(nsp != null) {
          // collect namespaces from database
          nsp.reset();
          int pp = p;
          do {
            final Atts atn = data.ns(pp);
            for(int n = 0; n < atn.size; ++n) {
              final byte[] key = atn.key[n];
              final byte[] val = atn.val[n];
              if(!nsp.contains(key)) {
                nsp.add(key);
                namespace(key, val);
                if(key.length == 0) empty = val;
              }
            }
            pp = data.parent(pp, data.kind(pp));
          } while(pp >= 0 && data.kind(pp) == Data.ELEM &&
              l == 0 && level() == 1);

          // check namespace of current element
          final byte[] key = pref(name);
          byte[] val = data.ns.uri(data.uri(p, k));
          if(val == null) val = EMPTY;
          if(key.length != 0) {
            if(ns.get(key) == -1) namespace(key, val);
          } else if(!eq(val, empty)) {
            namespace(key, val);
            empty = val;
          }
        } else if(l == 0 && ns(EMPTY) != EMPTY) {
          namespace(EMPTY, EMPTY);
        }

        // serialize attributes
        final int as = p + data.attSize(p, k);
        while(++p != as) {
          attribute(data.name(p, Data.ATTR), data.text(p, false));
        }
        parStack[l++] = r;
        names[l] = empty;
      }
    }

    // process remaining elements...
    while(--l >= 0) closeElement();
    return s;
  }