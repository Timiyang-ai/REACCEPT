public static Evaluator getDateFormatEvaluator() {
    return new Evaluator() {
      public String evaluate(String expression, Context context) {
        List l = parseParams(expression, context.getVariableResolver());
        if (l.size() != 2) {
          throw new DataImportHandlerException(SEVERE, "'formatDate()' must have two parameters ");
        }
        Object o = l.get(0);
        Object format = l.get(1);
        if (format instanceof VariableWrapper) {
          VariableWrapper wrapper = (VariableWrapper) format;
          o = wrapper.resolve();
          if (o == null)  {
            format = wrapper.varName;
            LOG.warn("Deprecated syntax used. The syntax of formatDate has been changed to formatDate(<var>, '<date_format_string>'). " +
                    "The old syntax will stop working in Solr 1.5");
          } else  {
            format = o.toString();
          }
        }
        String dateFmt = format.toString();
        SimpleDateFormat fmt = new SimpleDateFormat(dateFmt);
        Date date = null;
        if (o instanceof VariableWrapper) {
          VariableWrapper variableWrapper = (VariableWrapper) o;
          Object variableval = variableWrapper.resolve();
          if (variableval instanceof Date) {
            date = (Date) variableval;
          } else {
            String s = variableval.toString();
            try {
              date = DataImporter.DATE_TIME_FORMAT.get().parse(s);
            } catch (ParseException exp) {
              wrapAndThrow(SEVERE, exp, "Invalid expression for date");
            }
          }
        } else {
          String datemathfmt = o.toString();
          datemathfmt = datemathfmt.replaceAll("NOW", "");
          try {
            date = dateMathParser.parseMath(datemathfmt);
          } catch (ParseException e) {
            wrapAndThrow(SEVERE, e, "Invalid expression for date");
          }
        }
        return fmt.format(date);
      }

    };
  }