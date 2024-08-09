public void parse(final Item item, final Options options, final boolean ignore)
      throws QueryException {

    if(item == null) return;

    // XQuery map: convert to internal map
    if(item instanceof Map) {
      final Map map = (Map) item;
      final ValueIter vi = map.keys().iter();
      for(Item it; (it = vi.next()) != null;) {
        if(!(it instanceof AStr)) FUNCMP.thrw(info, map.description(), AtomType.STR, it.type);
        final Value v = map.get(it, info);
        if(!v.isItem()) FUNCMP.thrw(info, map.description(), AtomType.ITEM, v);
        final String key = string(it.string(null));
        final String val = string(((Item) v).string(info));
        if(!options.set(key, val) && options.predefined()) ELMOPTION.thrw(info, key);
      }
    } else {
      if(!test.eq(item)) ELMMAPTYPE.thrw(info, root, item.type);

      // interpret options
      final AxisIter ai = ((ANode) item).children();
      for(ANode n; (n = ai.next()) != null;) {
        if(n.type != NodeType.ELM) continue;
        final QNm qn = n.qname();
        if(!eq(qn.uri(), root.uri())) {
          if(ignore) continue;
          ELMOPTION.thrw(info, n);
        }
        // retrieve key from element name and value from "value" attribute or text node
        final String key = string(qn.local());
        byte[] val = n.attribute(VALUE);
        if(val == null) val = n.string();
        if(!options.set(key, string(val)) && options.predefined()) ELMOPTION.thrw(info, key);
      }
    }
  }