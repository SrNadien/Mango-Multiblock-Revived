package nadiendev.mangomultiblock.core.impl;

import nadiendev.mangomultiblock.core.SimpleMultiBlockPattern;

public interface IMultiBlockPatternBuilder {
    <T extends IMultiBlockPattern> T build(IPatternBuilder<T> builder);


    @SuppressWarnings("unchecked")
    default <T extends IMultiBlockPattern> T build() {
        return (T) build(SimpleMultiBlockPattern::new);
    };
}