package nadiendev.mangomultiblock.core.manager;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Rotation;
import nadiendev.mangomultiblock.core.impl.IMultiBlockPattern;

import org.jspecify.annotations.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiBlockManager {
    private static final HashMap<Identifier, MultiBlockManager> MANAGERS = new HashMap<>();

    public static MultiBlockManager getOrCreate(String modID, String managerID) {
        return MANAGERS.computeIfAbsent(Identifier.fromNamespaceAndPath(modID, managerID), MultiBlockManager::new);
    }

    public static List<MultiBlockManager> getManagers() {
        return List.copyOf(MANAGERS.values());
    }

    public static RegisteredMultiBlockPattern findAnyStructure(Level level, BlockPos blockPos, Rotation rotation) {
        for (MultiBlockManager manager : getManagers()) {
            var structure = manager.findStructure(level, blockPos, rotation);
            if (structure != null) return structure;
        }
        return null;
    }

    private final String modID;
    private final Identifier managerID;
    private final Map<Identifier, RegisteredMultiBlockPattern> MULTIBLOCKS = new HashMap<>();

    private MultiBlockManager(Identifier ID) {
        this.modID = ID.getNamespace();
        this.managerID = ID;
    }

    public Identifier getID() {
        return managerID;
    }

    public <E extends IMultiBlockPattern> E register(Identifier ID, E blockPattern) {
        if (MULTIBLOCKS.containsKey(ID)) throw new IllegalStateException("Already registered a Multiblock with ID: %s to Manager %s".formatted(ID, managerID));
        var rmbp = new RegisteredMultiBlockPattern(this, ID, blockPattern);
        MULTIBLOCKS.put(ID, rmbp);
        return blockPattern;
    }

    public <E extends IMultiBlockPattern> E register(String ID, E blockPattern) {
        return register(Identifier.fromNamespaceAndPath(modID, ID), blockPattern);
    }

    public @Nullable RegisteredMultiBlockPattern findStructure(Level level, BlockPos blockPos, Rotation rotation) {
        for (RegisteredMultiBlockPattern registeredMultiBlockPattern : MULTIBLOCKS.values()) {
            var result = registeredMultiBlockPattern.pattern().matches(level, blockPos, rotation);
            if (result) return registeredMultiBlockPattern;
        }
        return null;
    }

    public @Nullable RegisteredMultiBlockPattern getStructure(Identifier ID) {
        return MULTIBLOCKS.get(ID);
    }

    public @Nullable RegisteredMultiBlockPattern getStructure(String ID) {
        return getStructure(Identifier.fromNamespaceAndPath(modID, ID));
    }
}