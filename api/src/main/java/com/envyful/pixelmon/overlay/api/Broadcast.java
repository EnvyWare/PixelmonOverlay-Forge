package com.envyful.pixelmon.overlay.api;

import com.envyful.api.player.EnvyPlayer;

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

}
