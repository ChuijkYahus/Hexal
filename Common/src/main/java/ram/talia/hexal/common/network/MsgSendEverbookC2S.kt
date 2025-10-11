package ram.talia.hexal.common.network

import at.petrak.hexcasting.common.msgs.IMessage
import io.netty.buffer.ByteBuf
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerPlayer
import ram.talia.hexal.api.HexalAPI
import ram.talia.hexal.api.config.HexalConfig
import ram.talia.hexal.api.everbook.Everbook
import ram.talia.hexal.api.nbt.NbtSizeException
import ram.talia.hexal.api.nbt.decompressToNBT
import ram.talia.hexal.api.nbt.toCompressedBytes
import ram.talia.hexal.xplat.IXplatAbstractions

data class MsgSendEverbookC2S(val everbook: Everbook) : IMessage {
	private var wasTooBig = false
	private var somethingWeirdHappened = false
	override fun serialize(buf: FriendlyByteBuf) {
		val bookNbt = everbook.serialiseToNBT()
		val bytes = bookNbt.toCompressedBytes()
		HexalAPI.LOGGER.info("Serialized everbook data of size ${bookNbt.sizeInBytes()} compressed to size ${bytes.size}")
		buf.writeUUID(everbook.uuid)
		buf.writeByteArray(bytes)
	}

	override fun getFabricId() = ID

	fun handle(server: MinecraftServer, sender: ServerPlayer) {
		server.execute {
			IXplatAbstractions.INSTANCE.setFullEverbook(sender, everbook.filterIotasIllegalInterworld(server.overworld()))
			if (wasTooBig){
				HexalAPI.LOGGER.info("Player ${sender.displayName.string}'s everbook data exceeded the configured size limit of ${HexalConfig.server.everbookMaxSize}")
				sender.sendSystemMessage(Component.translatable("hexal.everbook.sizewarning", HexalConfig.server.everbookMaxSize))
			}
			if (somethingWeirdHappened){
				sender.sendSystemMessage(Component.translatable("hexal.everbook.unexpectederror"))
			}
		}
	}

	companion object {
		@JvmField
		val ID: ResourceLocation = HexalAPI.modLoc("sendever")

		@JvmStatic
		fun deserialise(buffer: ByteBuf): MsgSendEverbookC2S {
			val buf = FriendlyByteBuf(buffer)
			val uuid = buf.readUUID()
			val bytes = buf.readByteArray()
			var packet = MsgSendEverbookC2S(Everbook(uuid, mutableMapOf(), listOf()))
			try {
				val decompressed = bytes.decompressToNBT(HexalConfig.server.everbookMaxSize)
				HexalAPI.LOGGER.info("Deserializing everbook data of size ${bytes.size} decompressed to size ${decompressed.sizeInBytes()}")
				packet = MsgSendEverbookC2S(Everbook.fromNbt(decompressed))
			} catch (_ : NbtSizeException){
				packet.wasTooBig = true
			} catch (e : Exception){
				HexalAPI.LOGGER.error("An unexpected error occurred while attempting to decompress player $uuid's everbook data.", e)
				packet.somethingWeirdHappened = true
			}
			return packet
		}
	}
}