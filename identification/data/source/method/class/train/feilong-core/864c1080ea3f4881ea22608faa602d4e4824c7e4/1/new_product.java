public static String replace(CharSequence content,CharSequence target,CharSequence replacement){
        return null == content ? StringUtils.EMPTY
                        : content.toString().replace(target, null == replacement ? StringUtils.EMPTY : replacement);
    }