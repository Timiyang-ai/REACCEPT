public static void optimize(final Data data, final MainOptions options, final boolean enforce,
      final boolean enforceFT, final Optimize cmd) throws IOException {

    // initialize structural indexes
    final MetaData md = data.meta;
    if(!md.uptodate) {
      data.paths.init();
      data.elemNames.init();
      data.attrNames.init();
      md.dirty = true;

      final IntList pars = new IntList();
      final IntList elms = new IntList();
      int n = 0;

      for(int pre = 0; pre < md.size; ++pre) {
        final byte kind = (byte) data.kind(pre);
        final int par = data.parent(pre, kind);
        while(!pars.isEmpty() && pars.peek() > par) {
          pars.pop();
          elms.pop();
        }
        final int level = pars.size();
        if(kind == Data.DOC) {
          data.paths.put(0, Data.DOC, level);
          pars.push(pre);
          elms.push(0);
          ++n;
        } else if(kind == Data.ELEM) {
          final int id = data.name(pre);
          data.elemNames.index(data.elemNames.key(id), null, true);
          data.paths.put(id, Data.ELEM, level);
          pars.push(pre);
          elms.push(id);
        } else if(kind == Data.ATTR) {
          final int id = data.name(pre);
          final byte[] val = data.text(pre, false);
          data.attrNames.index(data.attrNames.key(id), val, true);
          data.paths.put(id, Data.ATTR, level, val, md);
        } else {
          final byte[] val = data.text(pre, true);
          if(kind == Data.TEXT && level > 1) data.elemNames.index(elms.peek(), val);
          data.paths.put(0, kind, level, val, md);
        }
        if(cmd != null) cmd.pre = pre;
      }
      md.ndocs.set(n);
      md.uptodate = true;
    }

    // reassign autooptimize flag
    final boolean autoopt = options.get(MainOptions.AUTOOPTIMIZE);
    if(autoopt != md.autoopt) {
      md.autoopt = autoopt;
      md.dirty = true;
    }

    // rebuild value indexes
    optimize(IndexType.ATTRIBUTE, data, options, md.createattr, md.attrindex, enforce, cmd);
    optimize(IndexType.TEXT,      data, options, md.createtext, md.textindex, enforce, cmd);
    optimize(IndexType.FULLTEXT,  data, options, md.createftxt, md.ftxtindex, enforceFT, cmd);
  }