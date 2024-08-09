public static String createRandomFromString(String str,int length){
        Validate.notBlank(str, "str can't be null/empty!");
        Validate.isTrue(length > 0, Slf4jUtil.formatMessage("length:[{}] can not <=0", length));
        return RandomStringUtils.random(length, str);
    }