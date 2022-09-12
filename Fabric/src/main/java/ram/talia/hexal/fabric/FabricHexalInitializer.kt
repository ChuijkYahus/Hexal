package ram.talia.hexal.fabric

import ram.talia.hexal.api.HexalAPI
import net.fabricmc.api.ModInitializer
import net.minecraft.core.Registry
import ram.talia.hexal.common.casting.RegisterPatterns
import net.minecraft.resources.ResourceLocation
import ram.talia.hexal.common.lib.HexalEntities
import ram.talia.hexal.common.lib.HexalSounds
import ram.talia.hexal.common.lib.HexalBlocks
import ram.talia.hexal.common.recipe.HexalRecipeSerializers
import java.util.function.BiConsumer

object FabricHexalInitializer : ModInitializer {
    override fun onInitialize() {
        HexalAPI.LOGGER.info("Hello Fabric World!")

        initListeners()

        initRegistries()

        RegisterPatterns.registerPatterns()
    }

    private fun initListeners() {

    }

    private fun initRegistries() {
        HexalSounds.registerSounds(bind(Registry.SOUND_EVENT))
        HexalBlocks.registerBlocks(bind(Registry.BLOCK))
        HexalBlocks.registerBlockItems(bind(Registry.ITEM))
        HexalEntities.registerEntities(bind(Registry.ENTITY_TYPE))

        HexalRecipeSerializers.registerSerializers(bind(Registry.RECIPE_SERIALIZER))
        HexalRecipeSerializers.registerTypes()
    }


    private fun <T> bind(registry: Registry<in T>): BiConsumer<T, ResourceLocation> =
        BiConsumer<T, ResourceLocation> { t, id -> Registry.register(registry, id, t) }
}