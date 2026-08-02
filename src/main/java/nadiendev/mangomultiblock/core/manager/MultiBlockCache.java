package nadiendev.mangomultiblock.core.manager;

import nadiendev.mangomultiblock.core.impl.IMultiBlockCache;
import nadiendev.mangomultiblock.core.impl.IMultiBlockPattern;

public class MultiBlockCache<T extends IMultiBlockPattern> implements IMultiBlockCache<T> {
    private T cachedResult;
    public MultiBlockCache() {}

    @Override
    public T get() {
        return cachedResult;
    }

    @Override
    public void updateStructure(T blockPattern) {
        this.cachedResult = blockPattern;
    }
}