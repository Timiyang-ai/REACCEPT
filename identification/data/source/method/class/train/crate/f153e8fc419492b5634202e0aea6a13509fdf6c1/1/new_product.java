@Deprecated
    public AnalyzedStatement analyze(Update node, Analysis analysis) {
        final TransactionContext transactionContext = analysis.transactionContext();
        StatementAnalysisContext statementAnalysisContext = new StatementAnalysisContext(
            analysis.parameterContext(),
            Operation.UPDATE,
            transactionContext);
        RelationAnalysisContext currentRelationContext = statementAnalysisContext.startRelation();
        AnalyzedRelation analyzedRelation = relationAnalyzer.analyze(node.relation(), statementAnalysisContext);

        FieldResolver fieldResolver = (FieldResolver) analyzedRelation;
        EvaluatingNormalizer normalizer = new EvaluatingNormalizer(
            functions,
            RowGranularity.CLUSTER,
            null,
            fieldResolver);
        FieldProvider columnFieldProvider = new NameFieldProvider(analyzedRelation);
        ExpressionAnalyzer columnExpressionAnalyzer = new ExpressionAnalyzer(
            functions,
            transactionContext,
            analysis.parameterContext(),
            columnFieldProvider,
            null);
        columnExpressionAnalyzer.setResolveFieldsOperation(Operation.UPDATE);

        assert Iterables.getOnlyElement(currentRelationContext.sources().values()) == analyzedRelation :
            "currentRelationContext.sources().values() must have one element and equal to analyzedRelation";
        ExpressionAnalyzer expressionAnalyzer = new ExpressionAnalyzer(
            functions,
            transactionContext,
            analysis.parameterContext(),
            new FullQualifiedNameFieldProvider(
                currentRelationContext.sources(),
                currentRelationContext.parentSources(),
                transactionContext.sessionContext().defaultSchema()),
            null);
        ExpressionAnalysisContext expressionAnalysisContext = new ExpressionAnalysisContext();

        int numNested = 1;
        if (analysis.parameterContext().numBulkParams() > 0) {
            numNested = analysis.parameterContext().numBulkParams();
        }

        WhereClauseAnalyzer whereClauseAnalyzer = null;
        if (analyzedRelation instanceof DocTableRelation) {
            whereClauseAnalyzer = new WhereClauseAnalyzer(functions, ((DocTableRelation) analyzedRelation));
        }
        TableInfo tableInfo = ((AbstractTableRelation) analyzedRelation).tableInfo();

        List<UpdateAnalyzedStatement.NestedAnalyzedStatement> nestedAnalyzedStatements = new ArrayList<>(numNested);
        for (int i = 0; i < numNested; i++) {
            analysis.parameterContext().setBulkIdx(i);

            Symbol querySymbol = expressionAnalyzer.generateQuerySymbol(node.whereClause(), expressionAnalysisContext);
            WhereClause whereClause = new WhereClause(normalizer.normalize(querySymbol, transactionContext));

            if (whereClauseAnalyzer != null) {
                whereClause = whereClauseAnalyzer.analyze(whereClause, transactionContext);
            }

            if (!whereClause.docKeys().isPresent() &&
                Symbols.containsColumn(whereClause.query(), DocSysColumns.VERSION)) {
                throw VERSION_SEARCH_EX;
            }

            UpdateAnalyzedStatement.NestedAnalyzedStatement nestedAnalyzedStatement =
                new UpdateAnalyzedStatement.NestedAnalyzedStatement(whereClause);

            for (Assignment assignment : node.assignements()) {
                analyzeAssignment(
                    assignment,
                    nestedAnalyzedStatement,
                    tableInfo,
                    normalizer,
                    expressionAnalyzer,
                    columnExpressionAnalyzer,
                    expressionAnalysisContext,
                    transactionContext
                );
            }
            nestedAnalyzedStatements.add(nestedAnalyzedStatement);
        }

        statementAnalysisContext.endRelation();
        return new UpdateAnalyzedStatement(analyzedRelation, nestedAnalyzedStatements);
    }