public static String URIEncodePath(String path) {
        // Bug #19188: Ideally, we'd like to use the standard class library to
        // encode the paths. We're aware of the following two methods:
        //
        // 1) URLEncoder.encode() - this method however transforms space to +
        // instead of %20 (which is right for HTML form data, but not for
        // paths), and it also does not preserve the separator chars (/).
        //
        // 2) URI.getRawPath() - transforms space and / as expected, but gets
        // confused when the path name contains a colon character (it thinks
        // parts of the path is schema in that case)
        //
        // For now, encode manually the way we want it.
        StringBuilder sb = new StringBuilder(path.length());
        for (byte b : path.getBytes(StandardCharsets.UTF_8)) {
            // URLEncoder's javadoc says a-z, A-Z, ., -, _ and * are safe
            // characters, so we preserve those to make the encoded string
            // shorter and easier to read. We also preserve the separator
            // chars (/). All other characters are encoded (as UTF-8 byte
            // sequences).
            if ((b == '/') || (b >= 'a' && b <= 'z')
                    || (b >= 'A' && b <= 'Z') || (b >= '0' && b <= '9')
                    || (b == '.') || (b == '-') || (b == '_') || (b == '*')) {
                sb.append((char) b);
            } else {
                sb.append('%');
                int u = b & 0xFF;  // Make the byte value unsigned.
                if (u <= 0x0F) {
                    // Add leading zero if required.
                    sb.append('0');
                }
                sb.append(
                        Integer.toHexString(u).toUpperCase(Locale.ENGLISH));
            }
        }
        return sb.toString();
    }