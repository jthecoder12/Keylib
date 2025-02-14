package org.indiumstudios.keylib.fs;

import org.jetbrains.annotations.Nullable;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.util.nfd.NFDFilterItem;

import java.nio.ByteBuffer;

import static org.lwjgl.util.nfd.NativeFileDialog.*;

// Based on the samples on LWJGL's GitHub page.

public final class FileDialog {
    private FileDialog() {}

    /**
     * Opens a file dialog.
     * @param type The type of files you want.
     * @param fileTypes The file types.
     * @return The filepath, an empty string, or null based on the result.
     */
    public static @Nullable String openFile(CharSequence type, CharSequence fileTypes) {
        try(MemoryStack stack = MemoryStack.stackPush()) {
            NFDFilterItem.Buffer fileFilters = NFDFilterItem.malloc(1);
            fileFilters.get(0).name(stack.UTF8(type)).spec(stack.UTF8(fileTypes));

            PointerBuffer outPath = stack.mallocPointer(1);
            return getOutput(NFD_OpenDialog(outPath, fileFilters, (ByteBuffer)null), outPath);
        }
    }

    /**
     * Saves a file.
     * @param type The type.
     * @param fileType The file extension type.
     * @param defaultName The default name that will be given to the file.
     * @return The filepath, an empty string, or null based on the result.
     */
    public static @Nullable String saveFile(CharSequence type, CharSequence fileType, CharSequence defaultName) {
        try(MemoryStack stack = MemoryStack.stackPush()) {
            NFDFilterItem.Buffer fileFilters = NFDFilterItem.malloc(1);
            fileFilters.get(0).name(stack.UTF8(type)).spec(stack.UTF8(fileType));

            PointerBuffer outPath = stack.mallocPointer(1);
            return getOutput(NFD_SaveDialog(outPath, fileFilters, null, defaultName), outPath);
        }
    }

    private static @Nullable String getOutput(int result, PointerBuffer outPath) {
        switch(result) {
            case NFD_OKAY:
                String filePath = outPath.getStringUTF8(0);
                NFD_FreePath(outPath.get(0));
                return filePath;
            case NFD_CANCEL:
                return "";
            default:
                System.err.printf("Error: %s%n", NFD_GetError());
                return null;
        }
    }
}
