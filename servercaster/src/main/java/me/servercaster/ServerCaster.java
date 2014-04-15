/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.servercaster;

import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Patrick Beuks (killje) and Floris Huizinga (Flexo013)
 */
public class ServerCaster extends JavaPlugin {

    private Caster anouncer;
    private static JavaPlugin instance;

    public static JavaPlugin getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        anouncer = new Caster();
        this.saveDefaultConfig();
        getCommand("cast").setExecutor(anouncer);
        getCommand("reloadservercast").setExecutor(anouncer);
    }

}
