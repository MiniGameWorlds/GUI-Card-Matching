package minigameworlds.cardmatching.menu;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class MenuModel {
    ItemStack WHITE_STAGE;
    ItemStack BLUE_STAGE;
    ItemStack PURPLE_STAGE;
    ItemStack GOLD_STAGE;

    private Inventory inventory;
    private Player player;

    public MenuModel(Inventory inventory, Player player) {
        StageData itemData = new StageData();

        WHITE_STAGE = itemData.getWhiteStageItem();
        BLUE_STAGE = itemData.getBlueStageItem();
        PURPLE_STAGE = itemData.getPurpleStageItem();
        GOLD_STAGE = itemData.getGoldStageItem();
        this.inventory = inventory;
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}

class StageData {
    private static String WHITE_STAGE_TITLE = ChatColor.RESET + "" + ChatColor.WHITE + "쉬움";
    private static String BLUE_STAGE_TITLE = ChatColor.RESET + "" + ChatColor.BLUE + "보통";
    private static String PURPLE_STAGE_TITLE = ChatColor.RESET + "" + ChatColor.LIGHT_PURPLE + "어려움";
    private static String GOLD_STAGE_TITLE = ChatColor.RESET + "" + ChatColor.YELLOW + "매우 어려움";

    private static List<String>
            WHITE_STAGE_LORE,   // 쉬움
            BLUE_STAGE_LORE,    // 보통
            PURPLE_STAGE_LORE,  // 어려움
            GOLD_STAGE_LORE;    // 매우 어려움

    public StageData() {
        WHITE_STAGE_LORE = new ArrayList<>();
        WHITE_STAGE_LORE.add(ChatColor.RESET + "2 X 5");

        BLUE_STAGE_LORE = new ArrayList<>();
        BLUE_STAGE_LORE.add(ChatColor.RESET + "2 X 7");

        PURPLE_STAGE_LORE = new ArrayList<>();
        PURPLE_STAGE_LORE.add(ChatColor.RESET + "4 X 5");

        GOLD_STAGE_LORE = new ArrayList<>();
        GOLD_STAGE_LORE.add(ChatColor.RESET + "4 X 7");
    }

    public static ItemStack getWhiteStageItem() {
        ItemStack itemStack = new ItemStack(Material.WHITE_TERRACOTTA);

        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(WHITE_STAGE_TITLE);
        meta.setLore(WHITE_STAGE_LORE);
        itemStack.setItemMeta(meta);

        return itemStack;
    }

    public static ItemStack getBlueStageItem() {
        ItemStack itemStack = new ItemStack(Material.BLUE_TERRACOTTA);

        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(BLUE_STAGE_TITLE);
        meta.setLore(BLUE_STAGE_LORE);
        itemStack.setItemMeta(meta);

        return itemStack;
    }

    public static ItemStack getPurpleStageItem() {
        ItemStack itemStack = new ItemStack(Material.PURPLE_TERRACOTTA);

        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(PURPLE_STAGE_TITLE);
        meta.setLore(PURPLE_STAGE_LORE);
        itemStack.setItemMeta(meta);

        return itemStack;
    }

    public static ItemStack getGoldStageItem() {
        ItemStack itemStack = new ItemStack(Material.YELLOW_TERRACOTTA);

        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(GOLD_STAGE_TITLE);
        meta.setLore(GOLD_STAGE_LORE);
        itemStack.setItemMeta(meta);

        return itemStack;
    }

}
