package me.jonasjones.betterconsolemc.modconfig;

import com.mojang.datafixers.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class ModConfigProvider implements SimpleConfig.DefaultConfig {

    private final List<Pair> configsList = new ArrayList<>();
    private String configContents = "";

    public List<Pair> getConfigsList() {
        return configsList;
    }

    public void addKeyValuePair(Pair<String, ?> keyValuePair, String comment) {
        configsList.add(keyValuePair);
        configContents += keyValuePair.getFirst() + "=" + keyValuePair.getSecond() + " #"
                + comment + " | default: " + keyValuePair.getSecond() + "\n";
    }

    public void addSingleLineComment(String comment) {
        configContents += "# " + comment + "\n";
    }

    @Override
    public String get(String namespace) {
        return configContents;
    }
}
