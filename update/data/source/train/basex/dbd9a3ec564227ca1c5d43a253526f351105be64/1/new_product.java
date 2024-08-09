private void serialize(final DBNode node) throws IOException {
    final FTPosData ft = node instanceof FTPosNode ? ((FTPosNode) node).ftpos : null;
    final Data data = node.data;
    int p = node.pre;
    int k = data.kind(p);
    if(k == Data.ATTR) throw SERATTR_X.getIO(node);

    boolean doc = false;
    final TokenSet nsp = data.nspaces.size() == 0 ? null : new TokenSet();
    final IntList pars = new IntList();
    final BoolList indt = new BoolList();

    // loop through all table entries
    final int s = p + data.size(p, k);
    while(p < s && !finished()) {
      k = data.kind(p);
      final int r = data.parent(p, k);

      // close opened elements...
      while(!pars.isEmpty() && pars.peek() >= r) {
        closeElement();
        indent = indt.pop();
        pars.pop();
      }

      if(k == Data.DOC) {
        if(doc) closeDoc();
        openDoc(data.text(p++, true));
        doc = true;
      } else if(k == Data.TEXT) {
        prepareText(data.text(p++, true), ft != null ? ft.get(data, p) : null);
      } else if(k == Data.COMM) {
        prepareComment(data.text(p++, true));
      } else {
        if(k == Data.PI) {
          preparePi(data.name(p, Data.PI), data.atom(p++));
        } else {
          // add element node
          final byte[] name = data.name(p, k);
          openElement(name);

          // add namespace definitions
          if(nsp != null) {
            // add namespaces from database
            nsp.clear();
            int pp = p;

            // check namespace of current element
            final byte[] u = data.nspaces.uri(data.uri(p, k));
            namespace(prefix(name), u == null ? EMPTY : u);

            do {
              final Atts ns = data.ns(pp);
              for(int n = 0; n < ns.size(); ++n) {
                final byte[] pref = ns.name(n);
                if(nsp.add(pref)) namespace(pref, ns.value(n));
              }
              // check ancestors only on top level
              if(lvl != 0) break;

              pp = data.parent(pp, data.kind(pp));
            } while(pp >= 0 && data.kind(pp) == Data.ELEM);
          }

          // serialize attributes
          indt.push(indent);
          final int as = p + data.attSize(p, k);
          while(++p != as) {
            final byte[] n = data.name(p, Data.ATTR);
            final byte[] v = data.text(p, false);
            attribute(n, v);
            if(eq(n, XML_SPACE) && indent) indent = eq(v, DataText.DEFAULT);
          }
          pars.push(r);
        }
      }
    }

    // process remaining elements...
    while(!pars.isEmpty()) {
      closeElement();
      indent = indt.pop();
      pars.pop();
    }
    if(doc) closeDoc();
  }