package org.mgqclub.makko;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;

@Mod(modid = Makko.MODID, name = Makko.NAME, version = Makko.VERSION)
public class Makko {
    public static final String MODID = "makko";
    public static final String NAME = "Makko";
    public static final String VERSION = "0.1.0";

    private static Logger logger;
        
    private static final String scriptsPath = "./mods/makko/";
    
    private List<String> scriptGroups;
    

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        
        scriptGroups = new ArrayList<String>();
        this.loadInitScripts();
        }

    @EventHandler
    public void init(FMLInitializationEvent event) {
    	
    }
    
    private int loadInitScripts() {
    	File scriptsFolder = new File(scriptsPath);
    	if (!scriptsFolder.exists()) {
    		logger.info("Create Makko ScriptGroups Folder.");
    		scriptsFolder.mkdir();
    		return 0;
    	} else {
    		boolean found = false;
    		for(File file : scriptsFolder.listFiles()) {
    			if (file.isDirectory()) {
    				File initScript = new File(scriptsPath + file.getName() + "/init.json");
    				if (initScript.exists()) {
    	    			found = true;
    					scriptGroups.add(file.getName());
    					logger.info("[" + file.getName() + "]" + "Loading...");
    				}
    			}
    		}
    		if (found) {
    			logger.info(scriptGroups.size() + " ScriptGroups Found");
    			return scriptGroups.size();
    		}
    		if (!found) logger.info("ScriptGroup Not Found.");
    		return 0;
    	}
    }
}
