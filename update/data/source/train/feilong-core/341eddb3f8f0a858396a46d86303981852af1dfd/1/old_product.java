public static String createRandomFromString(String str,int length){
        Validate.notEmpty(str, "str can't be null/empty!");
        Validate.isTrue(length > 0, Slf4jUtil.formatMessage("length:[{}] can not <=0", length));

        char[] ch = new char[length];
        int j = str.length();
        for (int i = 0; i < length; ++i){
            int index = JVM_RANDOM.nextInt(j);// 随机取个字符
            ch[i] = str.charAt(index);
        }
        return new String(ch);
    }