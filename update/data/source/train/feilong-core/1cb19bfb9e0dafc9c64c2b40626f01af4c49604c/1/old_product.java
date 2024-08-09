public static String formatWithIncludes(Object obj,final String...includes){
        return null == obj ? StringUtils.EMPTY : format(obj, buildJsonFormatConfig(null, includes));
    }