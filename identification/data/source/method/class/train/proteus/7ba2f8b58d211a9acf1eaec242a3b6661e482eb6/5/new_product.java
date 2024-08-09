@NonNull
        @Override
        public Value evaluate(Context context, Value data, int index) {
            Value[] arguments = resolve(context, this.arguments, data, index);
            return this.function.call(data, index, arguments);
        }