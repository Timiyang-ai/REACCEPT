public static String replace(CharSequence content,String target,Object replacement){
        if (null == content){
            return StringUtils.EMPTY;
        }
        // 替换序列是null
        String useReplacement = null == replacement ? StringUtils.EMPTY : replacement.toString();
        return content.toString().replace(target, useReplacement);
    }