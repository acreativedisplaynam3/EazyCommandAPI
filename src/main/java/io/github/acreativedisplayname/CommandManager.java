package io.github.acreativedisplayname;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles subcommand for the given main command.
 *
 * <p>
 * It checks the arguments a player types and runs the matching
 * {@link SubCommand}.
 * </p>
 *
 * <p>
 * You should create one {@code CommandManager} per command and
 * register your subcommands when the plugin starts.
 * </p>
 *
 * <p>
 * You do not need to call {@link #perform(CommandSender, String, String[])}
 * yourself. Bukkit will call it for you.
 * </p>
 *
 * @author acreativedisplaynam3
 * @since 0.10
 */
public class CommandManager {

    /**
     * Registered subcommands for a command.
     */
    private final List<SubCommand> subcommands = new ArrayList<>();

    /**
     * Registers a new subcommand with this command manager.
     * <p>
     * This method should typically be called during plugin startup.
     * </p>
     *
     * @param subCommand the subcommand to register
     * @throws IllegalArgumentException if {@code subCommand} is {@code null}
     */
    public void registerSubcommand(SubCommand subCommand) {
        subcommands.add(subCommand);
    }

    /**
     * Dispatches command execution to the appropriate {@link SubCommand}.
     * <p>
     * This method is intended for internal use only.
     * </p>
     *
     * <p>
     * If no arguments are provided, a simple help message listing all
     * registered subcommands is sent to the player.
     * </p>
     *
     * @param sender the command sender
     * @param label  the command label used
     * @param args   the command arguments
     * @return {@code true} to indicate the command was handled
     */
    public boolean perform(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            return true;
        }

        if (args.length > 0) {
            for (SubCommand subCommand : subcommands) {
                if (args[0].equalsIgnoreCase(subCommand.getName())) {
                    subCommand.execute(player, args);
                    return true;
                }
            }
            return true;
        }

        for (SubCommand subCommand : subcommands) {
            player.sendMessage(subCommand.getSyntax() + " - " + subCommand.getDescription());
        }
        return true;
    }
}
