public static String camelToUnderline(String param) {
        if (hasLength(param)) {
            char[] chars = param.toCharArray();
            StringBuilder sb = new StringBuilder();
            sb.append(Character.toLowerCase(chars[0]));
            char c;
            for (int i = 1, len = chars.length; i < len; i++) {
                c = chars[i];
                sb.append(Character.isUpperCase(c) ? "_" + Character.toLowerCase(c) : c);
            }
            return sb.toString();
        }
        return param;
    }