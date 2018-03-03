package me.kbrewster.api.protocol.b

import com.google.common.collect.Maps
import me.kbrewster.api.protocol.ProtocolAdapter
import me.kbrewster.api.protocol.ProtocolSettings
import me.kbrewster.api.protocol.Protocols
import net.minecraft.network.EnumConnectionState
import net.minecraft.network.EnumPacketDirection
import net.minecraft.network.handshake.client.C00Handshake
import net.minecraft.network.login.client.CPacketEncryptionResponse
import net.minecraft.network.login.client.CPacketLoginStart
import net.minecraft.network.login.server.SPacketEnableCompression
import net.minecraft.network.login.server.SPacketEncryptionRequest
import net.minecraft.network.login.server.SPacketLoginSuccess
import net.minecraft.network.play.client.*
import net.minecraft.network.play.server.*
import net.minecraft.network.status.client.CPacketPing
import net.minecraft.network.status.client.CPacketServerQuery
import net.minecraft.network.status.server.SPacketPong
import net.minecraft.network.status.server.SPacketServerInfo


object Protocolv47 : ProtocolAdapter() {

    override val protocol: Protocols
        get() = Protocols.`107`

    override val settings: ProtocolSettings
        get() = ProtocolSettings(true, true ,true)

    fun registerPackets() {

        try {
            this.stateConnectionMaps[EnumConnectionState.HANDSHAKING] =
                    Maps.newEnumMap(EnumPacketDirection::class.java)

            this.registerPacket(EnumConnectionState.HANDSHAKING, EnumPacketDirection.SERVERBOUND, C00Handshake::class.java)

            this.stateConnectionMaps[EnumConnectionState.PLAY] =
                    Maps.newEnumMap(EnumPacketDirection::class.java)

            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketKeepAlive::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketJoinGame::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketChat::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketTimeUpdate::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketEntityEquipment::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketSpawnPosition::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketUpdateHealth::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketRespawn::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketPlayerPosLook::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketHeldItemChange::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketUseBed::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketAnimation::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketSpawnPlayer::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketCollectItem::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketSpawnObject::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketSpawnMob::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketSpawnPainting::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketSpawnExperienceOrb::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketEntityVelocity::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketDestroyEntities::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketEntity::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketEntity.S15PacketEntityRelMove::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketEntity.S16PacketEntityLook::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketEntity.S17PacketEntityLookMove::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketEntityTeleport::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketEntityHeadLook::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketEntityStatus::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketEntityAttach::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketEntityMetadata::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketEntityEffect::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketRemoveEntityEffect::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketSetExperience::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketEntityProperties::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketChunkData::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketMultiBlockChange::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketBlockChange::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketBlockAction::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketBlockBreakAnim::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketMapChunkBulk::class.java)//
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketExplosion::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketEffect::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketSoundEffect::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketParticles::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketChangeGameState::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketSpawnGlobalEntity::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketOpenWindow::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketCloseWindow::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketSetSlot::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketWindowItems::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketWindowProperty::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketConfirmTransaction::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, CPacketUpdateSign::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketMaps::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketUpdateTileEntity::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketSignEditorOpen::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketStatistics::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketPlayerListItem::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketPlayerAbilities::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketTabComplete::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketScoreboardObjective::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketUpdateScore::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketDisplayObjective::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketTeams::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketCustomPayload::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketDisconnect::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketServerDifficulty::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketCombatEvent::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketCamera::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketWorldBorder::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketTitle::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketSetCompressionLevel::class.java) // set compression
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketPlayerListHeaderFooter::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketResourcePackSend::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.CLIENTBOUND, SPacketUpdateEntityNBT::class.java) // update entitynbt
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.SERVERBOUND, CPacketKeepAlive::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.SERVERBOUND, CPacketChatMessage::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.SERVERBOUND, CPacketUseEntity::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.SERVERBOUND, CPacketPlayer::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.SERVERBOUND, CPacketPlayer.Position::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.SERVERBOUND, CPacketPlayer.Rotation::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.SERVERBOUND, CPacketPlayer.PositionRotation::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.SERVERBOUND, CPacketPlayerDigging::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.SERVERBOUND, CPacketPlayerTryUseItemOnBlock::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.SERVERBOUND, CPacketHeldItemChange::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.SERVERBOUND, CPacketAnimation::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.SERVERBOUND, CPacketEntityAction::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.SERVERBOUND, CPacketInput::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.SERVERBOUND, CPacketCloseWindow::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.SERVERBOUND, CPacketClickWindow::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.SERVERBOUND, CPacketConfirmTransaction::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.SERVERBOUND, CPacketCreativeInventoryAction::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.SERVERBOUND, CPacketEnchantItem::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.SERVERBOUND, CPacketUpdateSign::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.SERVERBOUND, CPacketPlayerAbilities::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.SERVERBOUND, CPacketTabComplete::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.SERVERBOUND, CPacketClientSettings::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.SERVERBOUND, CPacketClientStatus::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.SERVERBOUND, CPacketCustomPayload::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.SERVERBOUND, CPacketSpectate::class.java)
            this.registerPacket(EnumConnectionState.PLAY, EnumPacketDirection.SERVERBOUND, CPacketResourcePackStatus::class.java)

            this.stateConnectionMaps[EnumConnectionState.LOGIN] =
                    Maps.newEnumMap(EnumPacketDirection::class.java)

            this.registerPacket(EnumConnectionState.LOGIN, EnumPacketDirection.CLIENTBOUND, SPacketDisconnect::class.java)
            this.registerPacket(EnumConnectionState.LOGIN, EnumPacketDirection.CLIENTBOUND, SPacketEncryptionRequest::class.java)
            this.registerPacket(EnumConnectionState.LOGIN, EnumPacketDirection.CLIENTBOUND, SPacketLoginSuccess::class.java)
            this.registerPacket(EnumConnectionState.LOGIN, EnumPacketDirection.CLIENTBOUND, SPacketEnableCompression::class.java)
            this.registerPacket(EnumConnectionState.LOGIN, EnumPacketDirection.SERVERBOUND, CPacketLoginStart::class.java)
            this.registerPacket(EnumConnectionState.LOGIN, EnumPacketDirection.SERVERBOUND, CPacketEncryptionResponse::class.java)

            this.stateConnectionMaps[EnumConnectionState.STATUS] =
                    Maps.newEnumMap(EnumPacketDirection::class.java)

            this.registerPacket(EnumConnectionState.STATUS, EnumPacketDirection.SERVERBOUND, CPacketServerQuery::class.java)
            this.registerPacket(EnumConnectionState.STATUS, EnumPacketDirection.CLIENTBOUND, SPacketServerInfo::class.java)
            this.registerPacket(EnumConnectionState.STATUS, EnumPacketDirection.SERVERBOUND, CPacketPing::class.java)
            this.registerPacket(EnumConnectionState.STATUS, EnumPacketDirection.CLIENTBOUND, SPacketPong::class.java)
        } catch(e: Exception ) {
            e.printStackTrace()
        }
    }
    init {
        registerPackets()
    }
}