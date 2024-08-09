@Override
	public Flux<ReactiveRedisConnection.BooleanResponse<RenameCommand>> rename(Publisher<RenameCommand> commands) {

		return connection.execute(cmd -> Flux.from(commands).flatMap(command -> {

			Assert.notNull(command.getKey(), "key must not be null.");
			Assert.notNull(command.getNewName(), "NewName must not be null");

			if (ClusterSlotHashUtil.isSameSlotForAllKeys(command.getKey(), command.getNewName())) {
				return super.rename(Mono.just(command));
			}

			Observable<Boolean> result = cmd.dump(command.getKey().array())
					.switchIfEmpty(Observable.error(new RedisSystemException("Cannot rename key that does not exist",
							new RedisException("ERR no such key."))))
					.concatMap(value -> cmd.restore(command.getNewName().array(), 0, value)
							.concatMap(res -> cmd.del(command.getKey().array())))
					.map(LettuceConverters.longToBooleanConverter()::convert);

			return LettuceReactiveRedisConnection.<Boolean> monoConverter().convert(result)
					.map(val -> new ReactiveRedisConnection.BooleanResponse<>(command, val));
		}));
	}