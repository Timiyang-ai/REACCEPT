public static String getMd5(List<String> lines) {
        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            // Remove empty lines
            line = line.trim();
            if (line.length() > 0) {
                sb.append(line).append("\n");
            }
        }
        MessageDigest md5 = getMd5Instance();
        md5.update(Utf8.toBytes(sb.toString()));
        return HexDump.toHexString(md5.digest()).toLowerCase();
    }