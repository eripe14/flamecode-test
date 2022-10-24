package com.eripe14.flamecode.command.argument;

import com.eripe14.flamecode.config.implementation.MessageConfig;
import dev.rollczi.litecommands.argument.ArgumentName;
import dev.rollczi.litecommands.argument.simple.OneArgument;
import dev.rollczi.litecommands.command.LiteInvocation;
import panda.std.Option;
import panda.std.Result;
import panda.utilities.text.Formatter;

@ArgumentName("number")
public class NumberArgument implements OneArgument<Integer> {

    private final MessageConfig messageConfig;

    public NumberArgument(MessageConfig messageConfig) {
        this.messageConfig = messageConfig;
    }

    @Override
    public Result<Integer, ?> parse(LiteInvocation invocation, String argument) {
        return Option.supplyThrowing(NumberFormatException.class, () -> Integer.parseInt(argument)).toResult(() -> {
            Formatter formatter = new Formatter();

            formatter.register("{ARG}", argument);

            return formatter.format(this.messageConfig.wrongUsage.argIsNotInteger);
        });
    }




}
