public static Duration parse(final CharSequence text) {
        Objects.requireNonNull(text, "text");
        int len = text.length();
        if (len < 4 ||
                (text.charAt(0) != 'P' && text.charAt(0) != 'p') ||
                (text.charAt(1) != 'T' && text.charAt(1) != 't') ||
                (text.charAt(len - 1) != 'S' && text.charAt(len - 1) != 's') ||
                (len == 5 && text.charAt(2) == '-' && text.charAt(3) == '0')) {
            throw new DateTimeParseException("Duration could not be parsed: " + text, text, 0);
        }
        String numberText = text.subSequence(2, len - 1).toString().replace(',', '.');
        if (numberText.charAt(0) == '+') {
            throw new DateTimeParseException("Duration could not be parsed: " + text, text, 2);
        }
        int dot = numberText.indexOf('.');
        try {
            if (dot == -1) {
                // no decimal places
                if (numberText.startsWith("-0")) {
                    throw new DateTimeParseException("Duration could not be parsed: " + text, text, 2);
                }
                return create(Long.parseLong(numberText), 0);
            }
            // decimal places
            boolean negative = false;
            if (numberText.charAt(0) == '-') {
                negative = true;
            }
            long secs = Long.parseLong(numberText.substring(0, dot));
            numberText = numberText.substring(dot + 1);
            len = numberText.length();
            if (len == 0 || len > 9 || numberText.charAt(0) == '-' || numberText.charAt(0) == '+') {
                throw new DateTimeParseException("Duration could not be parsed: " + text, text, 2);
            }
            int nanos = Integer.parseInt(numberText);
            switch (len) {
                case 1:
                    nanos *= 100000000;
                    break;
                case 2:
                    nanos *= 10000000;
                    break;
                case 3:
                    nanos *= 1000000;
                    break;
                case 4:
                    nanos *= 100000;
                    break;
                case 5:
                    nanos *= 10000;
                    break;
                case 6:
                    nanos *= 1000;
                    break;
                case 7:
                    nanos *= 100;
                    break;
                case 8:
                    nanos *= 10;
                    break;
            }
            return negative ? ofSeconds(secs, -nanos) : create(secs, nanos);

        } catch (ArithmeticException | NumberFormatException ex) {
            throw new DateTimeParseException("Duration could not be parsed: " + text, text, 2, ex);
        }
    }