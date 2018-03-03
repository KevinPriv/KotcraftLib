package me.kbrewster.api.utilities

import me.kbrewster.api.mc
import net.minecraft.entity.Entity

class EntityLimiter(val method: EntityLimiterMethods, var range: Int = -1) {


    /**
     * Gets Entities that are allowed to render
     */
    fun getRenderingEntities(entities: List<Entity>) = when(method) {
        EntityLimiterMethods.RANGE -> entities
                .filter { mc.player.getDistanceToEntity(it) <= range }
        EntityLimiterMethods.ENTITY_COUNT ->  entities
                .sortedBy { mc.player.getDistanceSqToEntity(it) }
                .subList(0, if(range < entities.size) range else entities.size)
        else -> entities
    }

    enum class EntityLimiterMethods {
        NONE,
        RANGE,
        ENTITY_COUNT,
    }
}