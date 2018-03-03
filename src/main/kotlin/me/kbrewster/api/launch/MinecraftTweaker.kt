package me.kbrewster.api.launch

import me.kbrewster.api.FORGE
import me.kbrewster.api.PROTOCOL_VERSION
import me.kbrewster.api.logger
import net.minecraft.launchwrapper.ITweaker
import net.minecraft.launchwrapper.Launch
import net.minecraft.launchwrapper.LaunchClassLoader
import org.spongepowered.asm.launch.MixinBootstrap
import org.spongepowered.asm.mixin.MixinEnvironment
import org.spongepowered.asm.mixin.Mixins
import java.io.File
import java.util.*


class MinecraftTweaker : ITweaker {

    private val args: ArrayList<String> = ArrayList()

    private val isRunningForge: Boolean =
            Launch.classLoader.transformers.
                    any { it.javaClass.name.contains("fml") }

    override fun acceptOptions(args: MutableList<String>, gameDir: File?, assetsDir: File?, profile: String?) {
        this.args.addAll(args)
        addArgs(mapOf("gameDir" to gameDir,
                "assetsDir" to assetsDir,
                "version" to profile))
    }

    override fun getLaunchTarget() =
            "net.minecraft.client.main.Main"

    override fun injectIntoClassLoader(classLoader: LaunchClassLoader?) {
        logger.info("Setting up Mixins...")
        MixinBootstrap.init()
        with(MixinEnvironment.getDefaultEnvironment()) {
            Mixins.addConfiguration("mixins.api.json")
            this.obfuscationContext = when {
                isRunningForge -> {
                    FORGE = true
                    "searge" // Switchs to forge searge mappings
                }
                else -> {
                    FORGE = false
                    "notch" // Switchs to notch mappings
                }
            }
            logger.info("Forge {}!", if (FORGE) "found" else "not found")
            this.side = MixinEnvironment.Side.CLIENT
        }
    }

    override fun getLaunchArguments(): Array<String> =
            if (FORGE) arrayOf()
            else args.toTypedArray()

    private fun addArg(label: String, value: String?) {
        if (!this.args.contains("--$label") && value != null) {
            this.args.add("--$label")
            this.args.add(value)
        }
    }

    private fun addArg(args: String, file: File?) =
            file?.let {
                addArg(args, file.absolutePath)
            }!!

    private fun addArgs(args: Map<String, Any?>) =
            args.forEach { label, value ->
                when (value) {
                    is String? -> addArg(label, value)
                    is File? -> addArg(label, value)
                }
            }
}