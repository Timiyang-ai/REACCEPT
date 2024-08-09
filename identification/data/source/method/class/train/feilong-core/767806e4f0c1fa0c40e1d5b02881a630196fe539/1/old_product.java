public static String substringWithoutLast(final CharSequence text,final String lastString){
        if (Validator.isNullOrEmpty(text)){
            return StringUtils.EMPTY;
        }

        //由于上面的循环中,最后一个元素可能是null或者empty,判断加还是不加拼接符有点麻烦,因此,循环中统一拼接,但是循环之后做截取处理
        String returnValue = text.toString();
        if (Validator.isNullOrEmpty(lastString)){
            return returnValue;
        }
        return returnValue.endsWith(lastString) ? substringWithoutLast(returnValue, lastString.length()) : returnValue;
    }