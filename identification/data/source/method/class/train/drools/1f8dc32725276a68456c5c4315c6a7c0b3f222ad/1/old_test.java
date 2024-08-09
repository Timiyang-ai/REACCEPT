@Test
    public void testBuild() throws Exception {
        final DrlParser parser = new DrlParser(LanguageLevelOption.DRL5);

        final PackageBuilder pkgBuilder = new PackageBuilder();
        pkgBuilder.addPackage( new PackageDescr( "org.drools" ) );
        Package pkg = pkgBuilder.getPackage();

        final PackageDescr pkgDescr = parser.parse( new InputStreamReader( getClass().getResourceAsStream( "nestedConditionalElements.drl" ) ) );

        // just checking there is no parsing errors
        assertFalse( parser.getErrors().toString(),
                            parser.hasErrors() );
        
        pkg.addGlobal( "results", List.class );

        final RuleDescr ruleDescr = pkgDescr.getRules().get( 0 );
        final String ruleClassName = "RuleClassName.java";
        ruleDescr.setClassName( ruleClassName );
        ruleDescr.addAttribute( new AttributeDescr( "dialect",
                                                    "java" ) );
        
        pkgBuilder.addPackage( pkgDescr );

        assertTrue( pkgBuilder.getErrors().toString(),
                    pkgBuilder.getErrors().isEmpty() );

        final Rule rule = pkgBuilder.getPackage().getRule( "test nested CEs" );

        assertEquals( "There should be 2 rule level declarations",
                      2,
                      rule.getDeclarations().size() );

        // second GE should be a not
        final GroupElement not = (GroupElement) rule.getLhs().getChildren().get( 1 );
        assertTrue( not.isNot() );
        // not has no outer declarations
        assertTrue( not.getOuterDeclarations().isEmpty() );
        assertEquals( 1,
                      not.getInnerDeclarations().size() );
        assertTrue( not.getInnerDeclarations().keySet().contains( "$state" ) );

        // second not
        final GroupElement not2 = (GroupElement) ((GroupElement) not.getChildren().get( 0 )).getChildren().get( 1 );
        assertTrue( not2.isNot() );
        // not has no outer declarations
        assertTrue( not2.getOuterDeclarations().isEmpty() );
        assertEquals( 1,
                      not2.getInnerDeclarations().size() );
        assertTrue( not2.getInnerDeclarations().keySet().contains( "$likes" ) );
    }