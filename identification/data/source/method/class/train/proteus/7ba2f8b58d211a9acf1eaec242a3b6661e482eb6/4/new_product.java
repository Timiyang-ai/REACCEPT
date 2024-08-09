public Result evaluate(Value data, int index) {
            Result result = resolveData(data, index);
            if (null == this.formatter) {
                return result;
            } else {
                Value resolved = this.formatter.format(result.value, index, resolveArguments(data, index));
                return Result.success(resolved);
            }
        }