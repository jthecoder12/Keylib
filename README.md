# Keylib

[libGDX](https://libgdx.com) based engine taken straight from Keybeats (not released yet).

Keylib is like an abstraction for libGDX. One main difference is that it has [ImGui](https://github.com/spair/imgui).

**All the files in this repo must be placed in a package called <code>org.indiumstudios.keylib</code>**

**Dependencies (place in the dependencies section of the corresponding build.gradle file):**
imgui-java (in global build.gradle):
<code>
api "io.github.spair:imgui-java-binding:\<version\>"
api "io.github.spair:imgui-java-lwjgl3:\<version\>"
api "io.github.spair:imgui-java-natives-linux:\<version\>"
api "io.github.spair:imgui-java-natives-macos:\<version\>"
api "io.github.spair:imgui-java-natives-windows:\<version\>"
</code>
Replace "\<version\>" with the current version on [the official GitHub page of imgui-java](https://github.com/spair/imgui), for example "1.89.0"

Taken from the [ImGui documentation on the libGDX wiki](https://libgdx.com/wiki/graphics/2d/imgui)

lwjgl (in core build.gradle):
<code>
implementation platform("org.lwjgl:lwjgl-bom:$lwjglVersion")

implementation "org.lwjgl:lwjgl"
implementation "org.lwjgl:lwjgl-glfw"
runtimeOnly "org.lwjgl:lwjgl::natives-windows"
runtimeOnly "org.lwjgl:lwjgl-glfw::natives-windows"
runtimeOnly "org.lwjgl:lwjgl::natives-macos"
runtimeOnly "org.lwjgl:lwjgl-glfw::natives-macos"
runtimeOnly "org.lwjgl:lwjgl::natives-macos-arm64"
runtimeOnly "org.lwjgl:lwjgl-glfw::natives-macos-arm64"
runtimeOnly "org.lwjgl:lwjgl::natives-linux"
runtimeOnly "org.lwjgl:lwjgl-glfw::natives-linux"
</code>

There is a class called <code>KeylibApplication</code>. Instead of your main game class extending off <code>AppicationAdapter</code>, you should extend it off <code>KeylibApplication</code>.
Read the JavaDoc [here](https://jthecoder12.github.io/keylib) for more information.
