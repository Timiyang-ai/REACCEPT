public Result evaluate(Value data, int index) {
            Result result = resolveData(tokens, data, index);
            if (null == this.function) {
                return result;
            } else {
                Value resolved = this.function.format(result.value, index, resolveArguments(arguments, data, index));
                return Result.success(resolved);
            }
        }