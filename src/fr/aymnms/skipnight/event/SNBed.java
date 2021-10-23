package fr.aymnms.skipnight.event;

import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerBedEnterEvent;
import fr.aymnms.skipnight.SNMain;
import org.bukkit.event.Listener;

public class SNBed implements Listener
{
    private SNMain main;

    public SNBed(final SNMain main) {
        this.main = main;
    }

    @EventHandler
    public void joinBed(final PlayerBedEnterEvent e) {
        final Player p = e.getPlayer();

        // if not night or not thundering -> stop
        if (p.getWorld().getTime() < 12000L && !p.getWorld().isThundering())
            return;

        final int playerLimit = Bukkit.getServer().getOnlinePlayers().size() / 2;

        if (!this.main.getPB().contains(p))
            this.main.getPB().add(p);

        System.out.println("join: Bedlist: " + this.main.getPB());

        if (this.main.getPB().size() == 1)
            Bukkit.broadcastMessage("§8[§rSkip§9Night§8] §9" + this.main.getPB().get(0).getName() + " §r§7souhaite faire passer la nuit §8[§9§l" + this.main.getPB().size() + "§8/§f" + playerLimit + "]");
        else if (this.main.getPB().size() > 1)
            Bukkit.broadcastMessage("§8[§rSkip§9Night§8] §9" + p.getName() + " §7vient de rejoindre le lit §8[§9§l" + this.main.getPB().size() + "§8/§f" + playerLimit + "]");

        if (this.main.getPB().size() >= playerLimit) {
            Bukkit.broadcastMessage("§8[§rSkip§9Night§8] §7Le jour se l\u00e8ve.");
            Bukkit.getWorld("world").setTime(0L);
            this.main.getPB().clear();
        }
    }

    @EventHandler
    public void leaveBed(final PlayerBedLeaveEvent e) {
        final Player p = e.getPlayer();
        final int yay = Bukkit.getServer().getOnlinePlayers().size() / 2;

        if (p.getWorld().getTime() > 12000L || p.getWorld().isThundering()){
            if (this.main.getPB().contains(p))
                this.main.getPB().remove(p);

            System.out.println("leave: Bedlist: " + this.main.getPB());

            if (this.main.getPB().size() == 0 && (p.getWorld().getTime() > 12000L || p.getWorld().isThundering()))
                Bukkit.broadcastMessage("§8[§rSkip§9Night§8] §7Plus personne ne souhaite se coucher !");
            else if (SNMain.getInstance().getPB().size() > 0 && p.getWorld().getTime() > 12000L)
                Bukkit.broadcastMessage("§8§l" + p.getName() + " §r§7vient de quitter le lit §8[§9§l" + this.main.getPB().size() + "§8/§f" + yay + "]");
        }

    }
}
