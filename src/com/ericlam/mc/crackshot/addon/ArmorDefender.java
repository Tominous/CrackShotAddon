package com.ericlam.mc.crackshot.addon;



import org.bukkit.Material;

import java.util.Map;
import java.util.Optional;

public class ArmorDefender {
    private String lore;
    private Map<Material,Double> armors;

    public ArmorDefender(String lore, Map<Material, Double> armors) {
        this.lore = lore;
        this.armors = armors;
    }

    public String getLore() {
        return lore;
    }

    public double getDamage(Material material){
        return Optional.ofNullable(armors.get(material)).orElse(0.0);
    }
}
