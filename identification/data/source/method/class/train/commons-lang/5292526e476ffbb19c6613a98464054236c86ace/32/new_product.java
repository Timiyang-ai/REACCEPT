private void init() {
        final Calendar definingCalendar = Calendar.getInstance(timeZone, locale);
        thisYear= definingCalendar.get(Calendar.YEAR);

        final StringBuilder regex= new StringBuilder();
        final List<Strategy> collector = new ArrayList<Strategy>();

        final Matcher patternMatcher= formatPattern.matcher(pattern);
        if(!patternMatcher.lookingAt()) {
            throw new IllegalArgumentException(
                    "Illegal pattern character '" + pattern.charAt(patternMatcher.regionStart()) + "'");
        }

        currentFormatField= patternMatcher.group();
        Strategy currentStrategy= getStrategy(currentFormatField, definingCalendar);
        for(;;) {
            patternMatcher.region(patternMatcher.end(), patternMatcher.regionEnd());
            if(!patternMatcher.lookingAt()) {
                nextStrategy = null;
                break;
            }
            final String nextFormatField= patternMatcher.group();
            nextStrategy = getStrategy(nextFormatField, definingCalendar);
            if(currentStrategy.addRegex(this, regex)) {
                collector.add(currentStrategy);
            }
            currentFormatField= nextFormatField;
            currentStrategy= nextStrategy;
        }
        if (patternMatcher.regionStart() != patternMatcher.regionEnd()) {
            throw new IllegalArgumentException("Failed to parse \""+pattern+"\" ; gave up at index "+patternMatcher.regionStart());
        }
        if(currentStrategy.addRegex(this, regex)) {
            collector.add(currentStrategy);
        }
        currentFormatField= null;
        strategies= collector.toArray(new Strategy[collector.size()]);
        parsePattern= Pattern.compile(regex.toString());
    }