package io.github.acreativedisplayname;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This annotation is used to give information about a subcommand.
 *
 * <p>
 * Every class that extends {@link SubCommand} MUST have this annotation.
 * The command system uses it to get the command name, description,
 * syntax, and permission.
 * </p>
 *
 * @author acreativedisplaynam3
 * @since 0.10
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ICommands {

    /**
     * The name of the subcommand.
     *
     * <p>
     * This is what the player types after the main command.
     * </p>
     *
     * <p>
     * Example: {@code /democommand helloWorld}
     * </p>
     *
     * @return the subcommand name
     */
    String getName();

    /**
     * A short description of what the command does.
     *
     * @return the command description
     */
    String getDescription();

    /**
     * How the command should be used.
     *
     * <p>
     * This usually shows the full command format.
     * </p>
     *
     * <p>
     * Example: {@code /democommand helloWorld}
     * </p>
     *
     * @return the command syntax
     */
    String getSyntax();

    /**
     * The permission needed to use this command.
     *
     * <p>
     * If the player does not have this permission,
     * the command will not run.
     * </p>
     *
     * @return the permission string
     */
    String getPermission();
}

