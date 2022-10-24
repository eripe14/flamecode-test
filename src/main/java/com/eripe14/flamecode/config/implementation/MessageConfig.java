package com.eripe14.flamecode.config.implementation;

import com.eripe14.flamecode.config.ReloadableConfig;
import net.dzikoysk.cdn.entity.Contextual;
import net.dzikoysk.cdn.entity.Description;
import net.dzikoysk.cdn.source.Resource;
import net.dzikoysk.cdn.source.Source;

import java.io.File;
import java.util.List;

public class MessageConfig implements ReloadableConfig {

    public WrongUsage wrongUsage = new WrongUsage();

    @Contextual
    public static class WrongUsage {
        @Description("# Błędne użycie komend")
        public String invalidUsage = "&4Nie poprawne użycie komendy &8>> &7{COMMAND}.";

        public String invalidUsageHeader = "&cNie poprawne użycie komendy!";

        public String invalidUsageEntry = "&8 >> &7";

        public String noPermission = "&4Nie masz permisji do wykonania tej komendy!";

        public String cantFindPlayer = "&4Nie znaleziono podanego gracza!";

        public String onlyForPlayer = "&4Komenda tylko dla graczy!";

        public String argIsNotInteger = "&4Argument &c{ARG}&4, musi być liczbą!";
    }

    @Override
    public Resource resource(File folder) {
        return Source.of(folder, "messages.yml");
    }

}
