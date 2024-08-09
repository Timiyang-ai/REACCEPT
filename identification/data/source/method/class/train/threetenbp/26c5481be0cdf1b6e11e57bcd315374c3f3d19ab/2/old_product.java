public int parse(DateTimeParseContext context, CharSequence text, int position) {
        if (parsers == null) {
            throw new UnsupportedOperationException("Formatter does not support parsing");
        }
        if (optional) {
            context.startOptional();
            int pos = position;
            for (DateTimeParser parser : parsers) {
                pos = parser.parse(context, text, pos);
                if (pos < 0) {
                    context.endOptional(false);
                    return position;  // return original position
                }
            }
            context.endOptional(true);
            return pos;
        } else {
            for (DateTimeParser parser : parsers) {
                position = parser.parse(context, text, position);
                if (position < 0) {
                    break;
                }
            }
            return position;
        }
    }