package minigameworlds.cardmatching.game;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Stack;

public class GameModel {
    GamePresenter presenter;

    private static int size;
    private static int stageSize;
    private final Inventory field;
    private final Player player;
    public static int workload = 0;
    private static Stack<Integer> selectedItems;

    public GameModel(Inventory field, int size, Player player, GamePresenter presenter) {
        this.field = field;
        this.player = player;
        this.presenter = presenter;

        GameModel.size = size;
        GameModel.stageSize = size;
        GameModel.selectedItems = new Stack<>();
    }

    public Stack<Integer> getSelectedItems() {
        return selectedItems;
    }

    public int getStageSize() {
        return stageSize;
    }

    public int getSize() {
        return size;
    }

    public Inventory getField() {
        return field;
    }

    public Player getPlayer() {
        return player;
    }

    public int getWorkload() {
        return workload;
    }

    public void setWorkload(int workload) {
        GameModel.workload = workload;
    }

    public void setSize(int size) {
        GameModel.size = size;
    }
}
