package nadiendev.mangomultiblock.core.manager;

import net.minecraft.resources.Identifier;
import nadiendev.mangomultiblock.core.impl.IMultiBlockPattern;

public record RegisteredMultiBlockPattern(MultiBlockManager manager, Identifier ID, IMultiBlockPattern pattern) { }