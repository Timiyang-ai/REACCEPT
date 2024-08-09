@Override
	public Flux<ReactiveRedisConnection.BooleanResponse<RenameCommand>> rename(Publisher<RenameCommand> commands) {

		return connection.execute(cmd -> Flux.from(commands).flatMap(command -> {

			Assert.notNull(command.getKey(), "key must not be null.");
			Assert.notNull(command.getNewName(), "NewName must not be null");

			if (ClusterSlotHashUtil.isSameSlotForAllKeys(command.getKey(), command.getNewName())) {
				return super.rename(Mono.just(command));
			}

			Flux<Boolean> result = cmd.dump(command.getKey())
					.otherwiseIfEmpty(Mono.error(new RedisSystemException("Cannot rename key that does not exist",
							new RedisException("ERR no such key."))))
					.flatMap(value -> cmd.restore(command.getNewName(), 0, value).flatMap(res -> cmd.del(command.getKey())))
					.map(LettuceConverters.longToBooleanConverter()::convert);

			return result.map(val -> new ReactiveRedisConnection.BooleanResponse<>(command, val));
		}));
	}