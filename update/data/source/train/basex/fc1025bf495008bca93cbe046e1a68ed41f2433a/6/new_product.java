private void write(final Graphics g) {
    // choose color for enabled text, depending on highlighting, link, or current syntax
    color = isEnabled() ?
      high ? GUIConstants.GREEN : link ? GUIConstants.color4 : syntax.getColor(text) :
      Color.gray;
    high = false;

    // retrieve first character of current token
    final int ch = text.curr();
    if(ch == TokenBuilder.MARK) high = true;

    final int cp = text.pos();
    final int cc = text.getCaret();
    if(y > 0 && y < h) {
      // mark selected text
      if(text.selectStart()) {
        int xx = x;
        while(!text.inSelect() && text.more()) xx += charW(g, text.next());
        int cw = 0;
        while(text.inSelect() && text.more()) cw += charW(g, text.next());
        g.setColor(GUIConstants.color(3));
        g.fillRect(xx, y - fontH * 4 / 5, cw, fontH);
        text.pos(cp);
      }

      // mark found text
      int xx = x;
      while(text.more() && text.searchStart()) {
        while(!text.inSearch() && text.more()) xx += charW(g, text.next());
        int cw = 0;
        while(text.inSearch() && text.more()) cw += charW(g, text.next());
        g.setColor(GUIConstants.color2A);
        g.fillRect(xx, y - fontH * 4 / 5, cw, fontH);
        xx += cw;
      }
      text.pos(cp);

      // retrieve first character of current token
      if(text.erroneous()) drawError(g);

      // don't write whitespaces
      if(ch >= ' ') {
        g.setColor(color);
        String n = text.nextString();
        int ww = w - x;
        if(x + wordW > ww) {
          // shorten string if it cannot be completely shown (saves memory)
          int c = 0;
          for(final int nl = n.length(); c < nl && ww > 0; c++) {
            ww -= charW(g, n.charAt(c));
          }
          n = n.substring(0, c);
        }
        if(ch != ' ') g.drawString(n, x, y);

        // underline linked text
        if(link) g.drawLine(x, y + 1, x + wordW, y + 1);

      } else if(ch <= TokenBuilder.ULINE) {
        g.setFont(font);
      }

      // show cursor
      if(cursor && text.edited()) {
        xx = x;
        while(text.more()) {
          if(cc == text.pos()) {
            drawCursor(g, xx);
            break;
          }
          xx += charW(g, text.next());
        }
        text.pos(cp);
      }
    }

    // handle matching parentheses
    if(ch == '(' || ch == '[' || ch == '{') {
      pars.add(x);
      pars.add(y);
      pars.add(cp);
      pars.add(ch);
    } else if((ch == ')' || ch == ']' || ch == '}') && !pars.isEmpty()) {
      final int open = ch == ')' ? '(' : ch == ']' ? '[' : '{';
      if(pars.peek() == open) {
        pars.pop();
        final int cr = pars.pop();
        final int yy = pars.pop();
        final int xx = pars.pop();
        if(cc == cp || cc == cr) {
          g.setColor(GUIConstants.color3);
          g.drawRect(xx, yy - fontH * 4 / 5, charW(g, open), fontH);
          g.drawRect(x, y - fontH * 4 / 5, charW(g, ch), fontH);
        }
      }
    }
    next();
  }