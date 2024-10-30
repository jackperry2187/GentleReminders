package jackperry2187.gentlereminders.clientCommands;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import jackperry2187.gentlereminders.clientCommands.customArguments.FormattingArgument;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

@Environment(value = EnvType.CLIENT)
public class ArgumentInit {
    public static LiteralArgumentBuilder<FabricClientCommandSource> getCommandsWithArguments() {
        return ClientCommandManager.literal("gentlereminders").executes(CommandInit::help)
                .then(ClientCommandManager.literal("help").executes(CommandInit::help))
                .then(ClientCommandManager.literal("get")
                        .then(ClientCommandManager.literal("TimeRemaining").executes(CommandInit::getTimeUntilNextMessage))
                        .then(ClientCommandManager.literal("ConfigPath").executes(CommandInit::getConfigPath))
                        .then(ClientCommandManager.literal("ConfigVersion").executes(CommandInit::getConfigVersion))
                        .then(ClientCommandManager.literal("TicksBetweenMessages").executes(CommandInit::getTicksBetweenMessages))
                        .then(ClientCommandManager.literal("Messages")
                                .then(ClientCommandManager.argument("pageNumber", IntegerArgumentType.integer(1))
                                .executes(CommandInit::getMessages)))
                        // TODO: add optional parameters that would allow you to search or filter messages by title/description/enabled/colors
                )
                .then(ClientCommandManager.literal("set")
                        .then(ClientCommandManager.literal("TicksBetweenMessages")
                                .then(ClientCommandManager.argument("ticks", IntegerArgumentType.integer())
                                .executes(CommandInit::setTicksBetweenMessages)))
                        .then(ClientCommandManager.literal("Message")
                                .then(ClientCommandManager.argument("id", IntegerArgumentType.integer())
                                .then(ClientCommandManager.argument("title", StringArgumentType.string())
                                .then(ClientCommandManager.argument("message", StringArgumentType.string())
                                .then(ClientCommandManager.argument("enabled", BoolArgumentType.bool())
                                .then(ClientCommandManager.argument("titleColor", FormattingArgument.formatting())
                                .then(ClientCommandManager.argument("messageColor", FormattingArgument.formatting())
                                .executes(CommandInit::setMessage)))))))) // overwrites a message by ID
                )
                .then(ClientCommandManager.literal("add")
                        .then(ClientCommandManager.literal("Message")
                                .then(ClientCommandManager.argument("title", StringArgumentType.string())
                                .then(ClientCommandManager.argument("message", StringArgumentType.string())
                                .then(ClientCommandManager.argument("enabled", BoolArgumentType.bool())
                                .then(ClientCommandManager.argument("titleColor", FormattingArgument.formatting())
                                .then(ClientCommandManager.argument("messageColor", FormattingArgument.formatting())
                                .executes(CommandInit::addMessage))))))) // adds a message to the list
                )
                .then(ClientCommandManager.literal("remove")
                        .then(ClientCommandManager.literal("Message")
                                .then(ClientCommandManager.argument("id", IntegerArgumentType.integer())
                                .executes(CommandInit::removeMessage))) // removes a message from the list by id
                )
                .then(ClientCommandManager.literal("enable")
                        .then(ClientCommandManager.literal("GentleReminders")
                                .executes(CommandInit::enableMod)) // enables the mod
                        .then(ClientCommandManager.literal("Message")
                                .then(ClientCommandManager.argument("id", IntegerArgumentType.integer())
                                .executes(CommandInit::enableMessage))) // enables a message by id
                )
                .then(ClientCommandManager.literal("disable")
                        .then(ClientCommandManager.literal("GentleReminders")
                                .executes(CommandInit::disableMod)) // disables the mod
                        .then(ClientCommandManager.literal("Message")
                                .then(ClientCommandManager.argument("id", IntegerArgumentType.integer())
                                .executes(CommandInit::disableMessage))) // disables a message by id
                );
    }
}
