package org.indiumstudios.keylib.rendering.UI;

import com.badlogic.gdx.files.FileHandle;
import imgui.ImFont;
import imgui.ImFontConfig;
import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import org.indiumstudios.keylib.Debug;
import org.lwjgl.system.Platform;

import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.glfwGetCurrentContext;

public final class ImGuiUI {
    private ImGuiUI() {}

    private final static ArrayList<FileHandle> extraFontFileHandles = new ArrayList<>();
    private final static ArrayList<Integer> extraFontFontSizes = new ArrayList<>();
    private final static ArrayList<ImFont> extraFonts = new ArrayList<>();

    private static ImGuiImplGlfw imGuiGlfw;
    private static ImGuiImplGl3 imGuiGl3;

    private static FileHandle fileHandle;
    private static int fontSize;

    public static void setCustomFont(FileHandle fileHandle, int fontSize) {
        ImGuiUI.fileHandle = fileHandle;
        ImGuiUI.fontSize = fontSize;
    }

    public static void addExtraFont(FileHandle fileHandle, int fontSize) {
        extraFontFileHandles.add(fileHandle);
        extraFontFontSizes.add(fontSize);
    }

    public static void initImGui() {
        imGuiGlfw = new ImGuiImplGlfw();
        imGuiGl3 = new ImGuiImplGl3();

        ImGui.createContext();
        ImGuiIO io = ImGui.getIO();

        io.setIniFilename(null);

        final ImFontConfig fontConfig = new ImFontConfig();

        if(Platform.get() != Platform.MACOSX) {
            Debug.log("Using FreeType renderer");
            io.getFonts().setFreeTypeRenderer(true);
        }

        if(fileHandle != null) io.getFonts().addFontFromMemoryTTF(fileHandle.readBytes(), fontSize, fontConfig);
        else io.getFonts().addFontDefault(fontConfig);
        if(!extraFontFileHandles.isEmpty()) for(int i=0; i<extraFontFileHandles.size(); i++) extraFonts.add(io.getFonts().addFontFromMemoryTTF(extraFontFileHandles.get(i).readBytes(), extraFontFontSizes.get(i), fontConfig));

        io.getFonts().build();

        fontConfig.destroy();

        imGuiGlfw.init(glfwGetCurrentContext(), true);
        imGuiGl3.init();
    }

    /**
     * Loop through ImGui. This should be called before rendering anything.
     * Credits to spaiR on GitHub.
     */
    public static void loop() {
        imGuiGl3.newFrame();
        imGuiGlfw.newFrame();
        ImGui.newFrame();
    }

    /**
     * Render ImGui. This should be called after ImGui stuff.
     * Credits to SpaiR on GitHub.
     */
    public static void render() {
        ImGui.render();
        imGuiGl3.renderDrawData(ImGui.getDrawData());
    }

    /**
     * Disposes ImGui.
     */
    public static void dispose() {
        imGuiGl3.shutdown();
        imGuiGl3 = null;
        imGuiGlfw.shutdown();
        imGuiGlfw = null;
        ImGui.destroyContext();
    }

    /**
     * Gets an extra font.
     * @param index The index of the extra font. The first extra font will be index 0.
     * @return The extra font.
     */
    public static ImFont getExtraFont(int index) {
        return extraFonts.get(index);
    }
}
