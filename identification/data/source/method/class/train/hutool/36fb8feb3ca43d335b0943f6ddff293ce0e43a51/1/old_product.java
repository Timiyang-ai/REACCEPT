public Entity parse(Object vo) {
		String tableName = vo.getClass().getSimpleName();
		tableName = StrUtil.lowerFirst(tableName);
		this.setTableName(tableName);
		
		this.putAll(InjectUtil.toMap(vo, false));
		return this;
	}