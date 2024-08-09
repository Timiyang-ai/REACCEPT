public RelBuilder aggregate(GroupKey groupKey, Iterable<AggCall> aggCalls) {
    final Registrar registrar = new Registrar();
    registrar.extraNodes.addAll(fields());
    registrar.names.addAll(peek().getRowType().getFieldNames());
    final GroupKeyImpl groupKey_ = (GroupKeyImpl) groupKey;
    final ImmutableBitSet groupSet =
        ImmutableBitSet.of(registrar.registerExpressions(groupKey_.nodes));
  label:
    if (Iterables.isEmpty(aggCalls) && !groupKey_.indicator) {
      final RelMetadataQuery mq = peek().getCluster().getMetadataQuery();
      if (groupSet.isEmpty()) {
        final Double minRowCount = mq.getMinRowCount(peek());
        if (minRowCount == null || minRowCount < 1D) {
          // We can't remove "GROUP BY ()" if there's a chance the rel could be
          // empty.
          break label;
        }
      }
      if (registrar.extraNodes.size() == fields().size()) {
        final Boolean unique = mq.areColumnsUnique(peek(), groupSet);
        if (unique != null && unique) {
          // Rel is already unique.
          return project(fields(groupSet.asList()));
        }
      }
      final Double maxRowCount = mq.getMaxRowCount(peek());
      if (maxRowCount != null && maxRowCount <= 1D) {
        // If there is at most one row, rel is already unique.
        return this;
      }
    }
    final ImmutableList<ImmutableBitSet> groupSets;
    if (groupKey_.nodeLists != null) {
      final int sizeBefore = registrar.extraNodes.size();
      final SortedSet<ImmutableBitSet> groupSetSet =
          new TreeSet<>(ImmutableBitSet.ORDERING);
      for (ImmutableList<RexNode> nodeList : groupKey_.nodeLists) {
        final ImmutableBitSet groupSet2 =
            ImmutableBitSet.of(registrar.registerExpressions(nodeList));
        if (!groupSet.contains(groupSet2)) {
          throw new IllegalArgumentException("group set element " + nodeList
              + " must be a subset of group key");
        }
        groupSetSet.add(groupSet2);
      }
      groupSets = ImmutableList.copyOf(groupSetSet);
      if (registrar.extraNodes.size() > sizeBefore) {
        throw new IllegalArgumentException(
            "group sets contained expressions not in group key: "
                + registrar.extraNodes.subList(sizeBefore,
                registrar.extraNodes.size()));
      }
    } else {
      groupSets = ImmutableList.of(groupSet);
    }
    for (AggCall aggCall : aggCalls) {
      if (aggCall instanceof AggCallImpl) {
        final AggCallImpl aggCall1 = (AggCallImpl) aggCall;
        registrar.registerExpressions(aggCall1.operands);
        if (aggCall1.filter != null) {
          registrar.registerExpression(aggCall1.filter);
        }
      }
    }
    project(registrar.extraNodes);
    rename(registrar.names);
    final Frame frame = stack.pop();
    final RelNode r = frame.rel;
    final List<AggregateCall> aggregateCalls = new ArrayList<>();
    for (AggCall aggCall : aggCalls) {
      final AggregateCall aggregateCall;
      if (aggCall instanceof AggCallImpl) {
        final AggCallImpl aggCall1 = (AggCallImpl) aggCall;
        final List<Integer> args =
            registrar.registerExpressions(aggCall1.operands);
        final int filterArg = aggCall1.filter == null ? -1
            : registrar.registerExpression(aggCall1.filter);
        if (aggCall1.distinct && !aggCall1.aggFunction.isQuantifierAllowed()) {
          throw new IllegalArgumentException("DISTINCT not allowed");
        }
        if (aggCall1.filter != null && !aggCall1.aggFunction.allowsFilter()) {
          throw new IllegalArgumentException("FILTER not allowed");
        }
        aggregateCall =
            AggregateCall.create(aggCall1.aggFunction, aggCall1.distinct,
                aggCall1.approximate, args,
                filterArg, groupSet.cardinality(), r, null, aggCall1.alias);
      } else {
        aggregateCall = ((AggCallImpl2) aggCall).aggregateCall;
      }
      aggregateCalls.add(aggregateCall);
    }

    assert ImmutableBitSet.ORDERING.isStrictlyOrdered(groupSets) : groupSets;
    for (ImmutableBitSet set : groupSets) {
      assert groupSet.contains(set);
    }
    RelNode aggregate = aggregateFactory.createAggregate(r,
        groupKey_.indicator, groupSet, groupSets, aggregateCalls);

    // build field list
    final ImmutableList.Builder<Field> fields = ImmutableList.builder();
    final List<RelDataTypeField> aggregateFields =
        aggregate.getRowType().getFieldList();
    int i = 0;
    // first, group fields
    for (Integer groupField : groupSet.asList()) {
      RexNode node = registrar.extraNodes.get(groupField);
      final SqlKind kind = node.getKind();
      switch (kind) {
      case INPUT_REF:
        fields.add(frame.fields.get(((RexInputRef) node).getIndex()));
        break;
      default:
        String name = aggregateFields.get(i).getName();
        RelDataTypeField fieldType =
            new RelDataTypeFieldImpl(name, i, node.getType());
        fields.add(new Field(ImmutableSet.<String>of(), fieldType));
        break;
      }
      i++;
    }
    // second, indicator fields (copy from aggregate rel type)
    if (groupKey_.indicator) {
      for (int j = 0; j < groupSet.cardinality(); ++j) {
        final RelDataTypeField field = aggregateFields.get(i);
        final RelDataTypeField fieldType =
            new RelDataTypeFieldImpl(field.getName(), i, field.getType());
        fields.add(new Field(ImmutableSet.<String>of(), fieldType));
        i++;
      }
    }
    // third, aggregate fields. retain `i' as field index
    for (int j = 0; j < aggregateCalls.size(); ++j) {
      final AggregateCall call = aggregateCalls.get(j);
      final RelDataTypeField fieldType =
          new RelDataTypeFieldImpl(aggregateFields.get(i + j).getName(), i + j,
              call.getType());
      fields.add(new Field(ImmutableSet.<String>of(), fieldType));
    }
    stack.push(new Frame(aggregate, fields.build()));
    return this;
  }