public static Date toDate(String dateString,String...parsePatterns){
        Validate.notBlank(dateString, "dateString can't be blank!");
        Validate.notNull(parsePatterns, "parsePatterns can't be null!");
        try{
            return DateUtils.parseDate(dateString, parsePatterns);
        }catch (ParseException e){
            String pattern = "parse dateString [{}] use patterns:[{}] to date exception,message:[{}]";
            throw new IllegalArgumentException(Slf4jUtil.format(pattern, dateString, parsePatterns, e.getMessage()), e);
        }
    }