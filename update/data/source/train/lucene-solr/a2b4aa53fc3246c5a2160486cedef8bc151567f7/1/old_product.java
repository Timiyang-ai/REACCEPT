public static Evaluator getDateFormatEvaluator() {
    return new Evaluator() {
      public String evaluate(VariableResolver resolver, String expression) {
        CacheEntry e = getCachedData(expression);
        String expr = e.key;
        SimpleDateFormat fmt = e.format;
        Matcher m = IN_SINGLE_QUOTES.matcher(expr);
        if (m.find()) {
          String datemathExpr = m.group(1);
          try {
            Date date = dateMathParser.parseMath(datemathExpr);
            return fmt.format(date);
          } catch (ParseException exp) {
            throw new DataImportHandlerException(
                    DataImportHandlerException.SEVERE,
                    "Invalid expression for date", exp);
          }
        } else {
          Object o = resolver.resolve(expr);
          if (o == null)
            return "";
          Date date = null;
          if (o instanceof Date) {
            date = (Date) o;
          } else {
            String s = o.toString();
            try {
              date = DataImporter.DATE_TIME_FORMAT.get().parse(s);
            } catch (ParseException exp) {
              throw new DataImportHandlerException(
                      DataImportHandlerException.SEVERE,
                      "Invalid expression for date", exp);
            }
          }
          return fmt.format(date);
        }
      }

      private CacheEntry getCachedData(String str) {
        CacheEntry result = cache.get(str);
        if (result != null)
          return result;
        Matcher m = FORMAT_METHOD.matcher(str);
        String expr, pattern;
        if (m.find()) {
          expr = m.group(1).trim();
          if (IN_SINGLE_QUOTES.matcher(expr).find()) {
            expr = expr.replaceAll("NOW", "");
          }
          pattern = m.group(2).trim();
          cache.put(str, new CacheEntry(expr, new SimpleDateFormat(pattern)));
          return cache.get(str);
        } else {
          throw new DataImportHandlerException(
                  DataImportHandlerException.SEVERE, "Invalid format String : "
                  + "${dataimporter.functions." + str + "}");
        }
      }

      Map<String, CacheEntry> cache = new HashMap<String, CacheEntry>();

      Pattern FORMAT_METHOD = Pattern.compile("^(.*?),(.*?)$");
    };
  }