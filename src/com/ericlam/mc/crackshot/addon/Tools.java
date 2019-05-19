package com.ericlam.mc.crackshot.addon;

import com.ericlam.mc.crackshot.addon.main.CSAddon;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Optional;

public class Tools {

    public static boolean isNotOwner(ItemStack item, Player player) {
        String name = player.getName();
        String tag = CSAddon.getStatTrakTag().replace("<owner>",name);
        if (item.getItemMeta() == null) return true;
        return item.getItemMeta().getLore().stream().noneMatch(l -> l.replaceAll("§[a-fA-F0-9]", "").matches(tag));
    }

    public static boolean isStatTrak(ItemStack item){
        if (item.getItemMeta() == null) return false;
        ItemMeta meta = item.getItemMeta();
        String tag = ".*" + CSAddon.getStatTrakTag().replace("<owner>", "(?<owner>.+)");
        List<String> lores = meta.getLore();
        return lores.stream().anyMatch(l -> l.replaceAll("§[a-fA-F0-9]", "").matches(tag));
    }

    public static Optional<String> getHoldingWeapon(Player player){
        return Optional.ofNullable(CSAddon.csDirector.returnParentNode(player));
    }

    public static Optional<ArmorDefender> getDefender(ItemStack item){
        if (item.getItemMeta() == null) return Optional.empty();
        ArmorDefender armorDefender = null;
        main:
        for (String line : item.getItemMeta().getLore()) {
            for (ArmorDefender defender : CSAddon.getArmorSet()) {
                if (line.matches(defender.getLore())) {
                    armorDefender = defender;
                    break main;
                }
            }
        }
        return Optional.ofNullable(armorDefender);
    }
}
