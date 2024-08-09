private void run() throws Exception {
    ctx.soptions.set(StaticOptions.DBPATH, sandbox().path() + "/data");
    parseArgs();
    init();

    final Performance perf = new Performance();
    ctx.options.set(MainOptions.CHOP, false);

    final SerializerOptions sopts = new SerializerOptions();
    sopts.set(SerializerOptions.METHOD, SerialMethod.XML);
    sopts.set(SerializerOptions.INDENT, YesNo.NO);
    sopts.set(SerializerOptions.OMIT_XML_DECLARATION, YesNo.NO);
    ctx.options.set(MainOptions.SERIALIZER, sopts);

    final XdmValue doc = new XQuery("doc('" + file(false, CATALOG) + "')", ctx).value();
    final String version = asString("*:catalog/@version", doc);
    Util.outln(NL + "QT3 Test Suite " + version);
    Util.outln("Test directory: " + file(false, "."));
    Util.out("Parsing queries");

    for(final XdmItem ienv : new XQuery("*:catalog/*:environment", ctx).context(doc))
      genvs.add(new QT3Env(ctx, ienv));

    for(final XdmItem item : new XQuery("for $f in //*:test-set/@file return string($f)",
        ctx).context(doc)) testSet(item.getString());

    final StringBuilder result = new StringBuilder();
    result.append(" Rate    : ").append(pc(correct, tested)).append(NL);
    result.append(" Total   : ").append(total).append(NL);
    result.append(" Tested  : ").append(tested).append(NL);
    result.append(" Wrong   : ").append(tested - correct).append(NL);
    result.append(" Ignored : ").append(ignored).append(NL);

    // save log data
    Util.outln(NL + "Writing log file '" + TESTID + ".log'...");
    try(PrintOutput po = new PrintOutput(new IOFile(TESTID + ".log"))) {
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
    }

    // save report
    if(report != null) {
      sopts.set(SerializerOptions.OMIT_XML_DECLARATION, YesNo.YES);
      final String file = "ReportingResults/results_" + NAME + '_' + VERSION + IO.XMLSUFFIX;
      new IOFile(file).write(report.create(ctx));
      Util.outln("Creating report '" + file + "'...");
    }

    Util.out(NL + result);
    Util.outln(" Time    : " + perf);

    if(slow != null && !slow.isEmpty()) {
      Util.outln(NL + "Slow queries:");
      for(final Entry<Long, String> l : slow.entrySet()) {
        Util.outln("- " + -(l.getKey() / 1000000) + " ms: " + l.getValue());
      }
    }

    ctx.close();
    sandbox().delete();
  }