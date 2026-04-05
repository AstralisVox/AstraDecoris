package me.astralisvox.astradecoris;

import org.bstats.bukkit.Metrics;
import org.bstats.charts.SimplePie;
import org.bukkit.plugin.java.JavaPlugin;

public final class AstraDecoris extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void onReload() {

    }

//    private void registerBStats() {
//        int pluginID;
//        Metrics metrics = new Metrics(this, pluginID);
//
//        metrics.addCustomChart(new SimplePie("chart_id", () -> "My Value"));
//    }
}
