package jackperry2187.gentlereminders.commands.client.customArguments;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Formatting;

import java.util.concurrent.CompletableFuture;

@Environment(value = EnvType.CLIENT)
public class FormattingArgument implements ArgumentType<Formatting> {
    @Override
    public Formatting parse(StringReader reader) throws CommandSyntaxException {
        final Formatting result = Formatting.byName(reader.readString());

        if(result == null) throw CommandSyntaxException.BUILT_IN_EXCEPTIONS.dispatcherParseException().create("Must input a color!");
        if(!result.isColor()) throw CommandSyntaxException.BUILT_IN_EXCEPTIONS.dispatcherParseException().create("Must input a color!");

        return result;
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        for (Formatting formatting : Formatting.values()) {
            if (formatting.getName().startsWith(builder.getRemaining().toLowerCase())) {
                if(formatting.isColor()) builder.suggest(formatting.getName());
            }
        }
        return builder.buildFuture();
    }

    public static FormattingArgument formatting() {
        return new FormattingArgument();
    }
}
