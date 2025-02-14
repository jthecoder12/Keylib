package org.indiumstudios.keylib;

import static org.lwjgl.glfw.GLFW.glfwGetCurrentContext;

public final class Debug {
    public static boolean debugOn = true;

    private Debug() {}

    /**
     * Logs a message with the word DEBUG by it.
     * @param x The thing that is to be logged.
     */
    public static void log(Object x) {
        if(debugOn) System.out.printf("DEBUG: %s\n", x);
    }

    /**
     * Prints debug info about Keylib.
     */
    public static void printDebugInfo() {
        if(debugOn) {
            log(String.format("GLFW window handle: %s", glfwGetCurrentContext()));
            log(String.format("GLFW window memory location: %s", "0x"+Long.toHexString(glfwGetCurrentContext())));
        }
    }
}
