Value cache(final int max) throws QueryException {
    final int mx = max >= 0 ? max : Integer.MAX_VALUE;

    // evaluates the query
    final Iter iter = iter();
    final ItemList items;
    Item item;

    // check if all results belong to the database of the input context
    final Data data = resources.globalData();
    if(defaultOutput && data != null) {
      final IntList pres = new IntList();
      while((item = next(iter)) != null && item.data() == data && pres.size() < mx) {
        pres.add(((DBNode) item).pre());
      }

      // all results processed: return compact node sequence
      final int ps = pres.size();
      if(item == null || ps == mx) {
        return ps == 0 ? Empty.SEQ : new DBNodes(data, pres.finish()).ftpos(ftPosData);
      }

      // otherwise, add nodes to standard iterator
      items = new ItemList();
      for(int p = 0; p < ps; p++) items.add(new DBNode(data, pres.get(p)));
      items.add(item);
    } else {
      items = new ItemList();
    }

    // use standard iterator
    while((item = next(iter)) != null && items.size() < mx) {
      item.cache(null, false);
      items.add(item);
    }
    return items.value();
  }