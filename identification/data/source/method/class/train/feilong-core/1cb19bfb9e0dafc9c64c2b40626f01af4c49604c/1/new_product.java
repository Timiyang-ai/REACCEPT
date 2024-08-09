public static String formatWithIncludes(Object obj,final String...includes){
        return null == obj ? EMPTY : format(obj, buildJsonFormatConfig(null, includes));
    }