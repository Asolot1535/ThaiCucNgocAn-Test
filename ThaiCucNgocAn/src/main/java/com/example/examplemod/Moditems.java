
package com.example.examplemod;

import net.minecraft.world.item.Item;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Moditems {

    // Tạo hệ thống đăng ký vật phẩm
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(
                    ForgeRegistries.ITEMS,
                    thaicucngocan.MODID
            );

    // Đăng ký vật phẩm thử nghiệm
    public static final RegistryObject<Item> TEST_ITEM =
        ITEMS.register(
                "test_item",
                () -> new thaicucngocanitem(
                        new Item.Properties()
                                .stacksTo(1)
                                .fireResistant()
                )
        );
        
    private Moditems() {
    }
}