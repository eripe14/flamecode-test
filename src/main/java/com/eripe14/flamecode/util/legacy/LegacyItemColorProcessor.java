package com.eripe14.flamecode.util.legacy;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public final class LegacyItemColorProcessor implements UnaryOperator<Component> {

    private final static Component RESET_ITALIC = Component.text()
            .decoration(TextDecoration.ITALIC, false)
            .build();

    @Override
    public Component apply(Component component) {
        return RESET_ITALIC.append(component.replaceText(builder -> builder.match(Pattern.compile(".*"))
                .replacement((matchResult, builder1) -> Legacy.component(matchResult.group()))));
    }

}
