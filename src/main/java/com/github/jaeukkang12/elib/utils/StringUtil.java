package com.github.jaeukkang12.elib.utils;

import org.bukkit.ChatColor;

import java.util.Arrays;
import java.util.List;

public class StringUtil {
    public static String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static List<String> color(String... strings) {
        return Arrays.stream(strings).map(s -> ChatColor.translateAlternateColorCodes('&', s)).toList();
    }

    public static List<String> color(List<String> strings) {
        return strings.stream().map(s -> ChatColor.translateAlternateColorCodes('&', s)).toList();
    }
}
