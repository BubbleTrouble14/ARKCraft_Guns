package com.bubble.gunmod;

import org.apache.logging.log4j.Logger;

import com.bubble.gunmod.common.GunTab;
import com.bubble.gunmod.proxy.CommonProxy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

@Mod(modid = Main.MODID, version = Main.VERSION)
public class Main {
    public static final String MODID = "gunmod";
    public static final String VERSION = "1.0";

    @Instance(Main.MODID)
    private static Main instance;

    @SidedProxy(clientSide = "com.bubble.gunmod.proxy.ClientProxy", serverSide = "com.bubble.gunmod.proxy.ServerProxy")
    public static CommonProxy proxy;

    public static CreativeTabs tabGuns = new GunTab("gunTab");
    public static SimpleNetworkWrapper modChannel;
    public static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
	logger = event.getModLog();
	proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
	proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
	proxy.postInit(event);
    }

    public static Main instance() {
	return instance;
    }
}
