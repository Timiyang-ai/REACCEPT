@Test
	public void testAddView() throws IOException, URISyntaxException, AnnotatorException {
		SimpleGazetteerAnnotator sga = new SimpleGazetteerAnnotator(defaultRm);
		assertTrue ("Wrong number of dictionaries loaded.",sga.dictionaries.size() == 1);
		assertTrue ("Wrong number of dictionaries loaded.",sga.dictionariesIgnoreCase.size() == 1);
		TextAnnotation ta = tab.createTextAnnotation("I hail from the university of illinois at champaign urbana.");
		sga.addView(ta);
		SpanLabelView view = (SpanLabelView) ta.getView(ViewNames.TREE_GAZETTEER);
		List<Constituent> entities = view.getConstituents();
		Constituent c1 = entities.get(0);
		assertEquals(c1.toString(),"university of illinois");
		Constituent c2 = entities.get(1);
		assertEquals(c2.toString(),"university of illinois at champaign urbana");
		Constituent c3 = entities.get(2);
		assertEquals(c3.toString(),"illinois");
		Constituent c4 = entities.get(3);
		assertEquals(c4.toString(),"champaign");
		Constituent c5 = entities.get(4);
		assertEquals(c5.toString(),"urbana");
		 
		assertEquals(c1.getLabel(),"organizations(IC)");
		assertEquals(c2.getLabel(),"organizations(IC)");
		assertEquals(c3.getLabel(),"places(IC)");
		assertEquals(c4.getLabel(),"places(IC)");
		assertEquals(c5.getLabel(),"places(IC)");

        Properties props = new Properties();
        props.setProperty( SimpleGazetteerAnnotatorConfigurator.PHRASE_LENGTH.key, "4" );
        props.setProperty(SimpleGazetteerAnnotatorConfigurator.PATH_TO_DICTIONARIES.key, "/testgazetteers/");
        props.setProperty(SimpleGazetteerAnnotatorConfigurator.IS_LAZILY_INITIALIZED.key,  SimpleGazetteerAnnotatorConfigurator.FALSE );
		sga = new SimpleGazetteerAnnotator(new ResourceManager( props ));
		assertTrue ("Wrong number of dictionaries loaded.",sga.dictionaries.size() == 1);
		assertTrue ("Wrong number of dictionaries loaded.",sga.dictionariesIgnoreCase.size() == 1);
		ta = tab.createTextAnnotation("I hail from the university of illinois at champaign urbana.");
		sga.addView(ta);
		view = (SpanLabelView) ta.getView(ViewNames.TREE_GAZETTEER);
		entities = view.getConstituents();
		c1 = entities.get(0);
		assertEquals(c1.toString(),"university of illinois");
		c2 = entities.get(1);
		assertEquals(c2.toString(),"illinois");
		c3 = entities.get(2);
		assertEquals(c3.toString(),"champaign");
		c4 = entities.get(3);
		assertEquals(c4.toString(),"urbana");
		
		assertEquals(c1.getLabel(),"organizations(IC)");
		assertEquals(c2.getLabel(),"places(IC)");
		assertEquals(c3.getLabel(),"places(IC)");
		assertEquals(c4.getLabel(),"places(IC)");
		
		ta = tab.createTextAnnotation("I hail from the University of Illinois at champaign urbana.");
		sga.addView(ta);
		view = (SpanLabelView) ta.getView(ViewNames.TREE_GAZETTEER);
		entities = view.getConstituents();
		c1 = entities.get(0);
		assertEquals(c1.toString(),"University of Illinois");
		assertEquals(c1.getLabel(),"organizations");
		c2 = entities.get(1);
		assertEquals(c1.toString(),"University of Illinois");
		assertEquals(c1.getLabel(),"organizations");
	}