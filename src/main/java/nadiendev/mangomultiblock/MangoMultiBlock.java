package nadiendev.mangomultiblock;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

import nadiendev.mangomultiblock.core.Constants;
import nadiendev.mangomultiblock.core.registry.ItemRegistry;

@Mod(Constants.MODID)
public class MangoMultiBlock {
    
    public MangoMultiBlock(IEventBus modEventBus) {
        // Registrar items
        ItemRegistry.init(modEventBus);
        
        // Setup común
        modEventBus.addListener(this::commonSetup);
    }
    
    private void commonSetup(FMLCommonSetupEvent event) {
        // Lógica de setup común aquí
    }
}