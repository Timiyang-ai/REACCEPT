public static Date toDate(String dateString,String...parsePatterns){
        Validate.notBlank(dateString, "dateString can't be blank!");
        Validate.notNull(parsePatterns, "parsePatterns can't be null!");
        try{
            return DateUtils.parseDate(dateString, parsePatterns);
        }catch (ParseException e){
            String pattern = "input dateString:[{}],parsePatterns:[{}] parseDate exception";
            throw new IllegalArgumentException(Slf4jUtil.format(pattern, dateString, parsePatterns), e);
        }
    }