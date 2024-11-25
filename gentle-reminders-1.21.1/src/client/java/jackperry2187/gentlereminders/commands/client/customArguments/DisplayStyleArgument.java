package jackperry2187.gentlereminders.commands.client.customArguments;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import java.util.concurrent.CompletableFuture;

@Environment(value = EnvType.CLIENT)
public class DisplayStyleArgument implements ArgumentType<String> {
    @Override
    public String parse(StringReader reader) throws CommandSyntaxException {
        final String entered = reader.readString();

        if(!entered.equals("default") && !entered.equals("chat")) throw CommandSyntaxException.BUILT_IN_EXCEPTIONS.dispatcherParseException().create("Must input default or chat!");

        return entered.equals("default") ? "defaultToast" : "chatMessage";
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        if("default".startsWith(builder.getRemaining().toLowerCase())) {
            builder.suggest("default");
        }

        if("chat".startsWith(builder.getRemaining().toLowerCase())) {
            builder.suggest("chat");
        }

        return builder.buildFuture();
    }

    public static DisplayStyleArgument displayStyle() {
        return new DisplayStyleArgument();
    }
}
