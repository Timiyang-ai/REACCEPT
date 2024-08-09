  public void runTestWithParams( String xmlFieldname, String resultFieldname, boolean xslInField,
      boolean xslFileInField, String xslFileField, String xslFilename, String xslFactory ) throws Exception {

    KettleEnvironment.init();

    //
    // Create a new transformation...
    //
    TransMeta transMeta = new TransMeta();
    transMeta.setName( "xslt" );

    PluginRegistry registry = PluginRegistry.getInstance();

    //
    // create an injector step...
    //
    String injectorStepname = "injector step";
    InjectorMeta im = new InjectorMeta();

    // Set the information of the injector.
    String injectorPid = registry.getPluginId( StepPluginType.class, im );
    StepMeta injectorStep = new StepMeta( injectorPid, injectorStepname, im );
    transMeta.addStep( injectorStep );

    //
    // Create a XSLT step
    //
    String xsltName = "xslt step";
    XsltMeta xm = new XsltMeta();

    String xsltPid = registry.getPluginId( StepPluginType.class, xm );
    StepMeta xsltStep = new StepMeta( xsltPid, xsltName, xm );
    transMeta.addStep( xsltStep );

    TextFileInputField[] fields = new TextFileInputField[3];

    for ( int idx = 0; idx < fields.length; idx++ ) {
      fields[idx] = new TextFileInputField();
    }

    fields[0].setName( "XML" );
    fields[0].setType( ValueMetaInterface.TYPE_STRING );
    fields[0].setFormat( "" );
    fields[0].setLength( -1 );
    fields[0].setPrecision( -1 );
    fields[0].setCurrencySymbol( "" );
    fields[0].setDecimalSymbol( "" );
    fields[0].setGroupSymbol( "" );
    fields[0].setTrimType( ValueMetaInterface.TRIM_TYPE_NONE );

    fields[1].setName( "XSL" );
    fields[1].setType( ValueMetaInterface.TYPE_STRING );
    fields[1].setFormat( "" );
    fields[1].setLength( -1 );
    fields[1].setPrecision( -1 );
    fields[1].setCurrencySymbol( "" );
    fields[1].setDecimalSymbol( "" );
    fields[1].setGroupSymbol( "" );
    fields[1].setTrimType( ValueMetaInterface.TRIM_TYPE_NONE );

    fields[2].setName( "filename" );
    fields[2].setType( ValueMetaInterface.TYPE_STRING );
    fields[2].setFormat( "" );
    fields[2].setLength( -1 );
    fields[2].setPrecision( -1 );
    fields[2].setCurrencySymbol( "" );
    fields[2].setDecimalSymbol( "" );
    fields[2].setGroupSymbol( "" );
    fields[2].setTrimType( ValueMetaInterface.TRIM_TYPE_NONE );

    xm.setFieldname( xmlFieldname );
    xm.setResultfieldname( resultFieldname );
    xm.setXSLField( xslInField );
    xm.setXSLFileField( xslFileField );
    xm.setXSLFieldIsAFile( xslFileInField );
    xm.setXslFilename( xslFilename );
    xm.setXSLFactory( xslFactory );

    TransHopMeta hi = new TransHopMeta( injectorStep, xsltStep );
    transMeta.addTransHop( hi );

    //
    // Create a dummy step 1
    //
    String dummyStepname1 = "dummy step 1";
    DummyTransMeta dm1 = new DummyTransMeta();

    String dummyPid1 = registry.getPluginId( StepPluginType.class, dm1 );
    StepMeta dummyStep1 = new StepMeta( dummyPid1, dummyStepname1, dm1 );
    transMeta.addStep( dummyStep1 );

    TransHopMeta hi1 = new TransHopMeta( xsltStep, dummyStep1 );
    transMeta.addTransHop( hi1 );

    // Now execute the transformation...
    Trans trans = new Trans( transMeta );

    trans.prepareExecution( null );

    StepInterface si = trans.getStepInterface( dummyStepname1, 0 );
    RowStepCollector dummyRc1 = new RowStepCollector();
    si.addRowListener( dummyRc1 );

    RowProducer rp = trans.addRowProducer( injectorStepname, 0 );
    trans.startThreads();

    // add rows
    List<RowMetaAndData> inputList = createData( xslFilename );
    Iterator<RowMetaAndData> it = inputList.iterator();
    while ( it.hasNext() ) {
      RowMetaAndData rm = it.next();
      rp.putRow( rm.getRowMeta(), rm.getData() );
    }
    rp.finished();

    trans.waitUntilFinished();

    // Compare the results
    List<RowMetaAndData> resultRows = dummyRc1.getRowsWritten();
    List<RowMetaAndData> goldenImageRows = createResultData1();

    checkRows( goldenImageRows, resultRows, 2 );
  }