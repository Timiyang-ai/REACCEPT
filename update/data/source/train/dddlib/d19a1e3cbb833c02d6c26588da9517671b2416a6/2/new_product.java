public List<TaskVO> queryTodoList(String user) {
		UserTransaction owner = null;
		try {
			owner = this.startUserTransaction();
			List<TaskVO> todos = new ArrayList<TaskVO>();
			todos.addAll(this.queryTodoListCall(user, null, null));
			todos.addAll(this.queryDelegateTodoList(user, null));
			this.commitUserTransaction(owner);
			return todos;
		} catch (RuntimeException e) {
			e.printStackTrace();
			this.rollbackUserTransaction(owner);
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			this.rollbackUserTransaction(owner);
			throw new RuntimeException(e.getCause());
		}
	}