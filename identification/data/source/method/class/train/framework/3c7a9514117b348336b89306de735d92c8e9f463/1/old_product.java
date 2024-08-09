public static String rfc5987Encode(String value) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < value.length();) {
            int cp = value.codePointAt(i);
            if (cp < 127 && (Character.isLetterOrDigit(cp) || cp == '.')) {
                builder.append((char) cp);
            } else {
                // Create string from a single code point
                String cpAsString = new String(new int[] { cp }, 0, 1);

                appendHexBytes(builder, cpAsString.getBytes(UTF8));
            }

            // Advance to the next code point
            i += Character.charCount(cp);
        }

        return builder.toString();
    }