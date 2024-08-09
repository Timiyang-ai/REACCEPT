private void run(final String[] args) throws Exception {
    ctx.globalopts.string(GlobalOptions.DBPATH, sandbox().path() + "/data");
    parseArguments(args);
    init();

    final Performance perf = new Performance();
    ctx.options.bool(MainOptions.CHOP, false);
    ctx.options.bool(MainOptions.INTPARSE, false);
    ctx.options.string(MainOptions.SERIALIZER, "omit-xml-declaration=no,indent=no");

    final XdmValue doc = new XQuery("doc('" + file(false, CATALOG) + "')", ctx).value();
    final String version = asString("*:catalog/@version", doc);
    Util.outln(NL + "QT3 Test Suite " + version);
    Util.outln("Test directory: " + new File(".").getCanonicalPath());
    Util.out("Parsing queries");

    for(final XdmItem ienv : new XQuery("*:catalog/*:environment", ctx).context(doc))
      genvs.add(new QT3Env(ctx, ienv));

    for(final XdmItem it : new XQuery("for $f in //*:test-set/@file return string($f)",
        ctx).context(doc)) testSet(it.getString());

    final StringBuilder result = new StringBuilder();
    result.append(" Rate    : ").append(pc(correct, tested)).append(NL);
    result.append(" Total   : ").append(total).append(NL);
    result.append(" Tested  : ").append(tested).append(NL);
    result.append(" Wrong   : ").append(tested - correct).append(NL);
    result.append(" Ignored : ").append(ignored).append(NL);

    // save log data
    Util.outln(NL + "Writing log file '" + testid + ".log'...");
    final PrintOutput po = new PrintOutput(testid + ".log");
    po.println("QT3TS RESULTS __________________________" + NL);
    po.println(result.toString());
    po.println("WRONG __________________________________" + NL);
    po.print(wrong.finish());
    if(all || !single.isEmpty()) {
      po.println("CORRECT ________________________________" + NL);
      po.print(right.finish());
    }
    if(ignoring) {
      po.println("IGNORED ________________________________" + NL);
      po.print(ignore.finish());
    }
    po.close();

    // save report
    if(report != null) {
      final String file = "ReportingResults/results_" +
          Prop.NAME + "_" + Prop.VERSION + IO.XMLSUFFIX;
      new IOFile(file).write(report.create(ctx).toArray());
      Util.outln("Creating report '" + file + "'...");
    }

    Util.out(NL + result);
    Util.outln(" Time    : " + perf);

    if(slow != null && !slow.isEmpty()) {
      Util.outln(NL + "Slow queries:");
      for(final Map.Entry<Long, String> l : slow.entrySet()) {
        Util.outln("- " + -(l.getKey() / 1000000) + " ms: " + l.getValue());
      }
    }

    ctx.close();
    sandbox().delete();
  }