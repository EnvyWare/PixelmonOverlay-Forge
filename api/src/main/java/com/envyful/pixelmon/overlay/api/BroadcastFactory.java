package com.envyful.pixelmon.overlay.api;

import com.envyful.api.player.EnvyPlayer;

/**
 *
 * Static factory for utility methods in relation to overlays
 *
 */
public class BroadcastFactory {

    private static BroadcastPlatformFactory platformFactory = null;

    /**
     *
     * Sets the cached platform specific platform factory implementation
     *
     * @param platformFactory The platform factory impl
     */
    public static void setPlatformFactory(BroadcastPlatformFactory platformFactory) {
        BroadcastFactory.platformFactory = platformFactory;
    }

    /**
     *
     * Creates a builder instancce with the {@param a} as the display
     *
     * @param a The display
     * @param <A> The type of the display
     * @return The builder created
     */
    public static <A> Broadcast.Builder<A> builder(A a) {
        return platformFactory.builder(a);
    }

    /**
     *
     * Sends a simple text overlay to a single player
     *
     * @param text The text in the overlay
     * @param target The target player
     */
    public static void broadcastText(String text, EnvyPlayer<?> target) {
        platformFactory.broadcastText(text, target);
    }

    /**
     *
     * Sends a simple text overlay to a group of players
     *
     * @param text The text in the overlay
     * @param targets The target players
     */
    public static void broadcastText(String text, EnvyPlayer<?>... targets) {
        platformFactory.broadcastText(text, targets);
    }

    /**
     *
     * Sends a simple text overlay to everyone online
     *
     * @param text The text in the overlay
     */
    public static void broadcastTextToAll(String text) {
        platformFactory.broadcastTextToAll(text);
    }

}
