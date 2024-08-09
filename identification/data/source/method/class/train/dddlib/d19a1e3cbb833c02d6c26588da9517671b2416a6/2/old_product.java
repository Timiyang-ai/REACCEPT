public List<TaskVO> queryTodoList(String user) {
		List<TaskVO> todos = new ArrayList<TaskVO>();
		todos.addAll(this.queryTodoListCall(user, null, null));
		todos.addAll(this.queryDelegateTodoList(user, null));
		return todos;
	}