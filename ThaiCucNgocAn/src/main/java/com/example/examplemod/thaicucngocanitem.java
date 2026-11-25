
package com.example.examplemod;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class thaicucngocanitem extends Item {

    private static final String PASSIVE_READY_AT =
            "ThaiCucPassiveReadyAt";

    private static final int PASSIVE_COOLDOWN_TICKS = 10 * 20;
    private static final int PASSIVE_DURATION_TICKS = 2 * 20;

    public thaicucngocanitem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(
            ItemStack stack,
            Level level,
            Entity entity,
            int slotId,
            boolean isSelected
    ) {
        super.inventoryTick(stack, level, entity, slotId, isSelected);

        if (level.isClientSide) {
            return;
        }

        if (!(entity instanceof Player player)) {
            return;
        }

        // Chỉ hoạt động ở Offhand
        if (player.getOffhandItem() != stack) {
            return;
        }

        // Lấy thời gian hiện tại
        long now = level.getGameTime();

        // Kiểm tra hồi chiêu
        long readyAt = player.getPersistentData()
                .getLong(PASSIVE_READY_AT);

        if (now < readyAt) {
            return;
        }

        // Kích hoạt nội tại
        player.addEffect(
                new MobEffectInstance(
                        MobEffects.DAMAGE_RESISTANCE,
                        PASSIVE_DURATION_TICKS,
                        0
                )
        );

        // Thiết lập hồi chiêu
        player.getPersistentData().putLong(
                PASSIVE_READY_AT,
                now + PASSIVE_COOLDOWN_TICKS
        );
    }
}
