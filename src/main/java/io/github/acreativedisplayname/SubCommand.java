package io.github.acreativedisplayname;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Represents a single subcommand.
 *
 * <p>
 * To create a subcommand, extend this class.
 * Every subcommand MUST have the {@link ICommands} annotation.
 * </p>
 *
 * <p>
 * You only need to implement {@link #onExecute(Player, String[])}.
 * Permission checks and other command logic are handled automatically.
 * </p>
 *
 * @author acreativedisplaynam3
 * @since 0.10
 */
public abstract class SubCommand {

    /**
     * Information about this command taken from the {@link ICommands}.
     */
    public final ICommands iCommands;

    /**
     * Creates a new subcommand.
     *
     * <p>
     * If the {@link ICommands} annotation is missing,
     * the plugin will throw an error on startup.
     * </p>
     *
     * @throws IllegalStateException if {@link ICommands} is missing
     */
    protected SubCommand() {
        iCommands = getClass().getAnnotation(ICommands.class);
        if (iCommands == null) {
            throw new IllegalStateException("Missing @ICommands on " + getClass().getName());
        }
    }

    /**
     * Gets the name of the command.
     *
     * @return the command name
     */
    public String getName() {
        return iCommands.getName();
    }

    /**
     * Gets the description of the command.
     *
     * @return a short description
     */
    public String getDescription() {
        return iCommands.getDescription();
    }

    /**
     * Gets the command syntax.
     *
     * <p>
     * This shows the player how to use the command.
     * </p>
     *
     * @return the command syntax
     */
    public String getSyntax() {
        return iCommands.getSyntax();
    }

    /**
     * Gets the permission required to use this command.
     *
     * @return the permission string
     */
    public String getPermission() { return iCommands.getPermission(); }

    /**
     * Runs the subcommand.
     *
     * <p>
     * This method is used internally by the command system.
     * You should NOT call this method yourself.
     * </p>
     *
     * <p>
     * It checks if the player has permission and then calls
     * {@link #onExecute(Player, String[])}.
     * </p>
     *
     * @param player the player running the command
     * @param args   the command arguments
     */
    public final void execute(Player player, String[] args) {
        if (!player.hasPermission(getPermission())) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou cannot execute this command."));
            return;
        }
        onExecute(player, args);
    }

    /**
     * What the command actually does.
     *
     * <p>
     * This method is called only after the permission check passes.
     * Put your command logic here.
     * </p>
     *
     * @param player the player running the command
     * @param args   the command arguments
     */
    protected abstract void onExecute(Player player, String[] args);
}


