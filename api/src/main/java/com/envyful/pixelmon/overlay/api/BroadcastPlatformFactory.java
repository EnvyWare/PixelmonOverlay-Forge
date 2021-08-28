package com.envyful.pixelmon.overlay.api;

import com.envyful.api.player.EnvyPlayer;

/**
 *
 * Represents a platform specific factory implementation of the BroadcastFactory
 *
 */
public interface BroadcastPlatformFactory {

    /**
     *
     * Creates a builder instancce with the {@param a} as the display
     *
     * @param a The display
     * @param <A> The type of the display
     * @return The builder created
     */
    <A> Broadcast.Builder<A> builder(A a);

    /**
     *
     * Sends a simple text overlay to a single player
     *
     * @param text The text in the overlay
     * @param target The target player
     */
    void broadcastText(String text, EnvyPlayer<?> target);

    /**
     *
     * Sends a simple text overlay to a group of players
     *
     * @param text The text in the overlay
     * @param targets The target players
     */
    void broadcastText(String text, EnvyPlayer<?>... targets);

    /**
     *
     * Sends a simple text overlay to everyone online
     *
     * @param text The text in the overlay
     */
    void broadcastTextToAll(String text);

}
