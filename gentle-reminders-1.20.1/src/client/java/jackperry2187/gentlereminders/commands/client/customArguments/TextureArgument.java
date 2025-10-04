package jackperry2187.gentlereminders.commands.client.customArguments;

import java.util.concurrent.CompletableFuture;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;

import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class TextureArgument implements ArgumentType<Identifier> {
    @Override
    public Identifier parse(StringReader reader) throws CommandSyntaxException {
        return Identifier.fromCommandInput(reader);
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        if(builder.getRemaining().toLowerCase().equals("")) {
            Registries.BLOCK.getIds().forEach(id -> builder.suggest(id.toString()));
        }
        else {
            Registries.BLOCK.getIds().forEach(id -> {
                if(id.toString().toLowerCase().startsWith(builder.getRemaining().toLowerCase())) {
                    builder.suggest(id.toString());
                }
            });
        }

        return builder.buildFuture();
    }

    public static TextureArgument textureArgument() {
        return new TextureArgument();
    }
}
