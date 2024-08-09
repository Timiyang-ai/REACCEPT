public List<Operator> getInputOperators() {
		return new AbstractList<Operator>() {

			@Override
			public Operator get(int index) {
				return Operator.this.inputs.get(index) == null ? null : Operator.this.inputs.get(index).getOperator();
			}

			@Override
			public int indexOf(Object o) {
				ListIterator<Output> e = Operator.this.inputs.listIterator();
				while (e.hasNext())
					if (o == e.next())
						return e.previousIndex();
				return -1;
			}

			@Override
			public int size() {
				return Operator.this.inputs.size();
			}
		};
	}