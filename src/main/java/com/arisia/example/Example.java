package com.arisia.example;

import com.arisia.example.listeners.ExampleListener;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.*;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.*;


public final class Example extends JavaPlugin implements CommandExecutor, TabCompleter {
    public static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;

        new ExampleListener(plugin);

        Bukkit.getLogger().info("started " + plugin.getName());
    }

    @Override
    public void onDisable() {
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        final List<String> completions = new ArrayList<>();
        if (command.getName().equalsIgnoreCase("example1")) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                completions.add(player.getName());
            }
        }

        if (command.getName().equalsIgnoreCase("example1")) {
            completions.add("teste");

        }

        return completions;
    }

    @Override
    public boolean onCommand(CommandSender s, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("example1")) {
            new BukkitRunnable()
            {
                @Override
                public void run() {
                    s.sendMessage("rodou comando example1 depois de 1 segundo");
                }
            }.runTaskLater(plugin, 20);
        }
        if (command.getName().equalsIgnoreCase("example2")) {
            new BukkitRunnable()
            {
                @Override
                public void run() {
                    s.sendMessage("rodou comando example2 depois de 2 segundo");
                }
            }.runTaskLater(plugin, 40);
        }
        return true;
    }

    private void tipsLoop() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.sendMessage("Obrigado por estar online!");
                }
            }
        }.runTaskTimer(plugin, 2000, 2000);
    }


}
