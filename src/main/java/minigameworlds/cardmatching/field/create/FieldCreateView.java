package minigameworlds.cardmatching.field.create;

import minigameworlds.cardmatching.field.blind.FieldBlindView;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitTask;
import minigameworlds.cardmatching.Main;

public class FieldCreateView {
    FieldCreatePresenter presenter;

    Player player;
    Inventory field, blindField;

    BukkitTask blindTask;

    public Player getPlayer() {
        return player;
    }

    public Inventory getField() {
        return field;
    }

    public Inventory getBlindField() {
        return blindField;
    }

    int stage;
    public FieldCreateView(Player player, int slot) {
        this.player = player;
        this.stage = slot;
        showField();
    }

    public Inventory showField() {
        field = createField(getStage());
        blindField.setContents(field.getContents());

        player.openInventory(field);

        FieldBlindView view = new FieldBlindView(this);
        Main.plugin.getServer().getPluginManager().registerEvents(view, Main.plugin);
        blindTask = view.runTaskTimer(Main.plugin, 4L, 4L);

        return field;
    }

    public Inventory createField(int stage) {
        presenter = new FieldCreatePresenter(this);

        Inventory field;

        switch (stage) {
            case 0:
                field = presenter.createWhiteStageInventory();
                blindField = Bukkit.createInventory(null, 54, ChatColor.GOLD + "난이도 : "+ ChatColor.WHITE + "백" + ChatColor.GOLD +" | 필드 생성중...");
                break;
            case 1:
                field = presenter.createBlueStageInventory();
                blindField = Bukkit.createInventory(null, 54, ChatColor.GOLD + "난이도 : "+ ChatColor.BLUE + "청" + ChatColor.GOLD +" | 필드 생성중...");
                break;
            case 2:
                field = presenter.createPurpleStageInventory();
                blindField = Bukkit.createInventory(null, 54, ChatColor.GOLD + "난이도 : "+ ChatColor.LIGHT_PURPLE + "자" + ChatColor.GOLD +" | 필드 생성중...");
                break;
            case 3:
                field = presenter.createGoldStageInventory();
                blindField = Bukkit.createInventory(null, 54, ChatColor.GOLD + "난이도 : "+ ChatColor.GOLD + "황" + ChatColor.GOLD +" | 필드 생성중...");
                break;
            default:
                field = presenter.createWhiteStageInventory();
                blindField = Bukkit.createInventory(null, 54, ChatColor.GOLD + "난이도 : "+ ChatColor.WHITE + "백" + ChatColor.GOLD +" | 필드 생성중...");
        }

        return field;
    }

    public int getStage() {
        return (stage / 2) - 5;
    }
}
