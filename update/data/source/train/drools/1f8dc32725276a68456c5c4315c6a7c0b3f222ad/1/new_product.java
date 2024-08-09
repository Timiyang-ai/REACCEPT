public RuleConditionElement build( RuleBuildContext context,
                                       BaseDescr descr,
                                       Pattern prefixPattern ) {

        final PatternDescr patternDescr = (PatternDescr) descr;

        if ( patternDescr.getObjectType() == null || patternDescr.getObjectType().equals( "" ) ) {
            context.addError(new DescrBuildError(context.getParentDescr(),
                    patternDescr,
                    null,
                    "ObjectType not correctly defined"));
            return null;
        }

        ObjectType objectType = null;

        final FactTemplate factTemplate = context.getPkg().getFactTemplate( patternDescr.getObjectType() );

        if ( factTemplate != null ) {
            objectType = new FactTemplateObjectType( factTemplate );
        } else {
            try {
                final Class< ? > userProvidedClass = context.getDialect().getTypeResolver().resolveType( patternDescr.getObjectType() );
                if ( !Modifier.isPublic(userProvidedClass.getModifiers()) ) {
                    context.addError(new DescrBuildError(context.getParentDescr(),
                                                         patternDescr,
                                                         null,
                                                         "The class '" + patternDescr.getObjectType() + "' is not public"));
                    return null;
                }
                PackageRegistry pkgr = context.getPackageBuilder().getPackageRegistry( ClassUtils.getPackage( userProvidedClass ) );
                org.drools.core.rule.Package pkg = pkgr == null ? context.getPkg() : pkgr.getPackage();
                final boolean isEvent = pkg.isEvent( userProvidedClass );
                objectType = new ClassObjectType( userProvidedClass,
                                                  isEvent );
            } catch ( final ClassNotFoundException e ) {
                // swallow as we'll do another check in a moment and then record the problem
            }
        }

        // lets see if it maps to a query
        if ( objectType == null ) {
            RuleConditionElement rce = null;
            // it might be a recursive query, so check for same names
            if ( context.getRule().getName().equals( patternDescr.getObjectType() ) ) {
                // it's a query so delegate to the QueryElementBuilder
                QueryElementBuilder qeBuilder = QueryElementBuilder.getInstance();
                rce = qeBuilder.build( context,
                                        descr,
                                        prefixPattern,
                                        (Query) context.getRule() );
            }

            if ( rce == null ) {
                Rule rule = context.getPkg().getRule( patternDescr.getObjectType() );
                if ( rule instanceof Query ) {
                    // it's a query so delegate to the QueryElementBuilder
                    QueryElementBuilder qeBuilder = QueryElementBuilder.getInstance();
                    rce = qeBuilder.build( context,
                                           descr,
                                           prefixPattern,
                                           (Query) rule );
                }

                // try package imports
                for ( String importName : context.getDialect().getTypeResolver().getImports() ) {
                    importName = importName.trim();
                    int pos = importName.indexOf( '*' );
                    if ( pos >= 0 ) {
                        String pkgName = importName.substring( 0,
                                                               pos - 1 );
                        PackageRegistry pkgReg = context.getPackageBuilder().getPackageRegistry( pkgName );
                        if ( pkgReg != null ) {
                            rule = pkgReg.getPackage().getRule( patternDescr.getObjectType() );
                            if ( rule instanceof Query ) {
                                // it's a query so delegate to the QueryElementBuilder
                                QueryElementBuilder qeBuilder = QueryElementBuilder.getInstance();
                                rce = qeBuilder.build( context,
                                                       descr,
                                                       prefixPattern,
                                                       (Query) rule );
                                break;
                            }
                        }
                    }
                }

            }

            if ( rce == null ) {
                // this isn't a query either, so log an error
                context.addError(new DescrBuildError(context.getParentDescr(),
                                                     patternDescr,
                                                     null,
                                                     "Unable to resolve ObjectType '" + patternDescr.getObjectType() + "'"));
            }
            return rce;
        }

        Pattern pattern;

        boolean duplicateBindings = context.getDeclarationResolver().isDuplicated( context.getRule(),
                                                                                   patternDescr.getIdentifier(),
                                                                                   ((ClassObjectType) objectType).getClassName() );

        if ( !StringUtils.isEmpty( patternDescr.getIdentifier() ) && !duplicateBindings ) {

            pattern = new Pattern( context.getNextPatternId(),
                                   0, // offset is 0 by default
                                   objectType,
                                   patternDescr.getIdentifier(),
                                   patternDescr.isInternalFact() );
            if ( objectType instanceof ClassObjectType ) {
                // make sure PatternExtractor is wired up to correct ClassObjectType and set as a target for rewiring
                context.getPkg().getClassFieldAccessorStore().getClassObjectType( ((ClassObjectType) objectType),
                                                                                  (AcceptsClassObjectType) pattern.getDeclaration().getExtractor() );
            }
        } else {
            pattern = new Pattern( context.getNextPatternId(),
                                   0, // offset is 0 by default
                                   objectType,
                                   null );
        }

        if ( ClassObjectType.Match_ObjectType.isAssignableFrom( pattern.getObjectType() ) ) {
            PropertyHandler handler = PropertyHandlerFactory.getPropertyHandler( RuleTerminalNodeLeftTuple.class );
            if ( handler == null ) {
                PropertyHandlerFactoryFixer.getPropertyHandlerClass().put( RuleTerminalNodeLeftTuple.class,
                                                                           new ActivationPropertyHandler() );
            }
        }

        // adding the newly created pattern to the build stack this is necessary in case of local declaration usage
        context.getBuildStack().push( pattern );

        if ( duplicateBindings ) {
            processDuplicateBindings( patternDescr.isUnification(), patternDescr, pattern, patternDescr, "this", patternDescr.getIdentifier(), context );
        }

        if ( objectType instanceof ClassObjectType ) {
            // make sure the Pattern is wired up to correct ClassObjectType and set as a target for rewiring
            context.getPkg().getClassFieldAccessorStore().getClassObjectType( ((ClassObjectType) objectType),
                                                                              pattern );
        }

        if ( pattern.getObjectType() instanceof ClassObjectType ) {
            Class< ? > cls = ((ClassObjectType) pattern.getObjectType()).getClassType();
            if ( cls.getPackage() != null && !cls.getPackage().getName().equals( "java.lang" ) ) {
                // register the class in its own package unless it is primitive or belongs to java.lang
                TypeDeclaration typeDeclr = context.getPackageBuilder().getAndRegisterTypeDeclaration( cls,
                                                                                                       cls.getPackage().getName() );
                context.setTypesafe( typeDeclr == null || typeDeclr.isTypesafe() );
            } else {
                context.setTypesafe( true );
            }
        }

        processAnnotations( context, patternDescr, pattern );
        
        if ( patternDescr.getSource() != null ) {
            // we have a pattern source, so build it
            RuleConditionBuilder builder = (RuleConditionBuilder) context.getDialect().getBuilder( patternDescr.getSource().getClass() );

            PatternSource source = (PatternSource) builder.build( context,
                                                                  patternDescr.getSource() );
            if ( source instanceof From ) {
                ((From) source).setResultPattern( pattern );
            }
            pattern.setSource( source );
        }

        // Process all constraints
        processConstraintsAndBinds( context, patternDescr, pattern );

        for ( BehaviorDescr behaviorDescr : patternDescr.getBehaviors() ) {
            if ( pattern.getObjectType().isEvent() ) {
                if ( Behavior.BehaviorType.TIME_WINDOW.matches( behaviorDescr.getSubType() ) ) {
                    SlidingTimeWindow window = new SlidingTimeWindow( TimeUtils.parseTimeString( behaviorDescr.getParameters().get( 0 ) ) );
                    pattern.addBehavior( window );
                } else if ( Behavior.BehaviorType.LENGTH_WINDOW.matches( behaviorDescr.getSubType() ) ) {
                    SlidingLengthWindow window = new SlidingLengthWindow( Integer.valueOf( behaviorDescr.getParameters().get( 0 ) ) );
                    pattern.addBehavior( window );
                }
                context.setNeedStreamMode();
            } else {
                // Some behaviors can only be assigned to patterns declared as events
                context.addError(new DescrBuildError(context.getParentDescr(),
                        patternDescr,
                        null,
                        "A Sliding Window can only be assigned to types declared with @role( event ). The type '" + pattern.getObjectType() + "' in '" + context.getRule().getName()
                                + "' is not declared as an Event."));
            }
        }

        // poping the pattern
        context.getBuildStack().pop();

        return pattern;
    }