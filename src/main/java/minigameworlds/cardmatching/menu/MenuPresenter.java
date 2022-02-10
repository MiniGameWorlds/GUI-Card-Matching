package minigameworlds.cardmatching.menu;

import minigameworlds.cardmatching.field.create.FieldCreateView;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import minigameworlds.cardmatching.game.GameModel;

public class MenuPresenter implements Listener {
    MenuView view;
    MenuModel model;

    public MenuPresenter(Inventory inventory, Player player, MenuView view) {
        this.view = view;
        model = new MenuModel(inventory, player);
    }

    @EventHandler
    public void onCloseEvent(InventoryCloseEvent event) {
        event.getHandlers().unregister(this);
        model.getPlayer().closeInventory();
    }

    @EventHandler
    public void onClickEvent(InventoryClickEvent event) {
        event.setCursor(null);
        event.setCurrentItem(null);

        switch (event.getRawSlot()) {
            case 10 :
            case 12 :
            case 14 :
            case 16 :
                GameModel.workload = 0;
                new FieldCreateView(model.getPlayer(), event.getRawSlot());
                event.getHandlers().unregister(this);
                return;
            case 18 :
                event.getHandlers().unregister(this);
                model.getPlayer().sendMessage("메뉴를 닫았습니다.");
                model.getPlayer().closeInventory();
                return;
        }

        event.getHandlers().unregister(this);
        view.showMenu(model.getPlayer());
    }

    public Inventory setPlayerStatus(Inventory inventory, int slot) {
        ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD);

        SkullMeta meta = (SkullMeta) playerHead.getItemMeta();
        meta.setOwner(model.getPlayer().getName());
        meta.setDisplayName(model.getPlayer().getName()+"");

        playerHead.setItemMeta(meta);

        inventory.setItem(slot, playerHead);

        return inventory;
    }

    public Inventory setExit(Inventory inventory, int slot) {
        ItemStack exit = new ItemStack(Material.BARRIER);
        ItemMeta meta = exit.getItemMeta();

        meta.setDisplayName(ChatColor.RESET+ "" +ChatColor.RED + "종료");
        exit.setItemMeta(meta);

        inventory.setItem(slot, exit);

        return inventory;
    }

    public Inventory setBorder(Inventory inventory, Material borderMaterial) {
        ItemStack borderItem = new ItemStack(borderMaterial);

        for(int i = 0; i < inventory.getSize(); i++) {
            switch(i/9) {
                case 0:
                    inventory.setItem(i, borderItem);
                    continue;
            }
            if(i/9 == (inventory.getSize() / 9 - 1)) {
                inventory.setItem(i, borderItem);
                continue;
            }
            switch (i%9) {
                case 0:
                case 8:
                    inventory.setItem(i, borderItem);
                    continue;
            }
        }

        return inventory;
    }

    public Inventory setStage(Inventory inventory) {
        inventory.setItem(10, model.WHITE_STAGE);
        inventory.setItem(12, model.BLUE_STAGE);
        inventory.setItem(14, model.PURPLE_STAGE);
        inventory.setItem(16, model.GOLD_STAGE);

        return inventory;
    }
}
