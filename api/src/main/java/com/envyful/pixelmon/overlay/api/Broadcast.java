package com.envyful.pixelmon.overlay.api;

import com.envyful.api.player.EnvyPlayer;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 * Representing a Pixelmon overlay to be sent to the users
 *
 */
public interface Broadcast {

    /**
     *
     * Send the overlay to a specific player
     *
     * @param player The player
     */
    void send(EnvyPlayer<?> player);

    /**
     *
     * Send the overlay to a group of players
     *
     * @param players The players to send to
     */
    void send(EnvyPlayer<?>... players);

    /**
     *
     * Send the overlay to all online players
     *
     */
    void sendAll();

    /**
     *
     * Represents a Broadcast builder
     *
     * @param <T> type that the builder represents
     */
    interface Builder<T> {

        /**
         *
         * Specifies the duration of the {@link Broadcast}
         *
         * @param duration The duration
         * @param timeUnit the unit of the duration
         * @return The builder
         */
        Builder<T> duration(long duration, TimeUnit timeUnit);

        /**
         *
         * Specifies the duration of the {@link Broadcast} in seconds
         *
         * @param durationSeconds the dueation in seconds
         * @return The builder
         */
        Builder<T> duration(int durationSeconds);

        /**
         *
         * Specifies the lines of text of the {@link Broadcast}
         *
         * @param lines The lines of the broadcast
         * @return The builder
         */
        Builder<T> lines(String... lines);

        /**
         *
         * Specifies the lines of text of the {@link Broadcast}
         *
         * @param lines The lines of the broadcast
         * @return The builder
         */
        Builder<T> lines(List<String> lines);

        /**
         *
         * Specifies the display attributes of the image on the {@link Broadcast}
         *
         * @param display The display details
         * @return The builder
         */
        Builder<T> display(T display);

        /**
         *
         * Builds the {@link Broadcast} from the provided info
         *
         * @return The built {@link Broadcast}
         */
        Broadcast build();

    }
}
