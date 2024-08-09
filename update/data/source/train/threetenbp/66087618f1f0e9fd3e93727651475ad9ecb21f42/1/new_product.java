public int parse(DateTimeParseContext context, String parseText, int position) {
        int length = parseText.length();
        if (position > length) {
            throw new IndexOutOfBoundsException();
        }
        if (context.isStrict()) {
            TextStore textStore = rule.getTextStore(context.getLocale(), textStyle);
            if (textStore != null) {
                long match = textStore.matchText(!context.isCaseSensitive(), parseText.substring(position));
                if (match == 0) {
                    return ~position;
                } else if (match > 0) {
                    position += (match >>> 32);
                    context.setParsedField(rule, (int) match);
                    return position;
                }
            }
        } else {
            for (TextStyle textStyle : TextStyle.values()) {
                TextStore textStore = rule.getTextStore(context.getLocale(), textStyle);
                if (textStore != null) {
                    long match = textStore.matchText(!context.isCaseSensitive(), parseText.substring(position));
                    if (match > 0) {
                        position += (match >>> 32);
                        context.setParsedField(rule, (int) match);
                        return position;
                    }
                }
            }
        }
        return numberPrinterParser().parse(context, parseText, position);
    }