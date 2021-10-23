package fr.aymnms.skipnight;

import fr.aymnms.skipnight.event.SNBed;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class EventManager {

    public static void registerEvents(final SNMain main) {
        final PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents((Listener) new SNBed(main), (Plugin) main);
    }

}
