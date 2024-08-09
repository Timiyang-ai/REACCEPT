@Override
    public int parse(DateTimeParseContext context, CharSequence text, int position) {
        if (optional) {
            context.startOptional();
            int pos = position;
            for (DateTimePrinterParser pp : printerParsers) {
                pos = pp.parse(context, text, pos);
                if (pos < 0) {
                    context.endOptional(false);
                    return position;  // return original position
                }
            }
            context.endOptional(true);
            return pos;
        } else {
            for (DateTimePrinterParser pp : printerParsers) {
                position = pp.parse(context, text, position);
                if (position < 0) {
                    break;
                }
            }
            return position;
        }
    }