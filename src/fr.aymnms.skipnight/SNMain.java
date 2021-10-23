package fr.aymnms.skipnight;

import org.bukkit.entity.Player;
import java.util.ArrayList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class SNMain extends JavaPlugin implements Listener
{
    public static SNMain instance;
    private ArrayList<Player> PlayerBed;

    public SNMain() {
        this.PlayerBed = new ArrayList<Player>();
    }

    public static SNMain getInstance() {
        return SNMain.instance;
    }

    @Override
    public void onEnable() {
        EventManager.registerEvents(this);
    }

    public ArrayList<Player> getPB() {
        return this.PlayerBed;
    }
}
