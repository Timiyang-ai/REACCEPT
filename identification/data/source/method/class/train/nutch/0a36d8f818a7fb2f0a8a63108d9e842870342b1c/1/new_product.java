@Override
  public NutchDocument filter(NutchDocument doc, String url, WebPage page)
      throws IndexingException {
    HashSet<String> set = null;
    
    for (Entry<CharSequence, CharSequence> e : page.getInlinks().entrySet()) {
      String anchor = TableUtil.toString(e.getValue());
      
      if(anchor.equals(""))
        continue;
      
      if (deduplicate) {
        if (set == null) set = new HashSet<String>();
        String lcAnchor = anchor.toLowerCase();

        // Check if already processed the current anchor
        if (!set.contains(lcAnchor)) {
          doc.add("anchor", anchor);

          // Add to set
          set.add(lcAnchor);
        }
      } else {
        doc.add("anchor", anchor);
      }
    }
    
    return doc;
  }