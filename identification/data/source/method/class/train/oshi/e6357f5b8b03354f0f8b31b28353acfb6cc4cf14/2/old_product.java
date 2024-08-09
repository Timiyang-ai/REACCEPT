public static byte[] hexStringToByteArray(String digits) {
        // Check if string is valid hex
        if (!VALID_HEX.matcher(digits).matches()) {
            LOG.error("Invalid hexadecimal string: {}", digits);
            return null;
        }
        int len = digits.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(digits.charAt(i), 16) << 4)
                    | Character.digit(digits.charAt(i + 1), 16));
        }
        return data;
    }