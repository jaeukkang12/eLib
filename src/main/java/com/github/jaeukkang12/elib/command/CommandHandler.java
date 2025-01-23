package com.github.jaeukkang12.elib.command;

import com.github.jaeukkang12.elib.utils.StringUtil;
import org.bukkit.command.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Method;
import java.security.Permission;
import java.util.*;

public class CommandHandler implements CommandExecutor {

    private final String ERROR = StringUtil.color("&c명령어를 실행하던 중 오류가 발생했습니다.");
    private final String WRONG_COMMAND = StringUtil.color("&c알 수 없는 명령어입니다!");
    private final String NO_PERMISSION = StringUtil.color("&c명령어를 사용할 수 없습니다!");

    private final Map<String, Map<String, Method>> commandMap = new HashMap<>();
    private final Map<String, Map<String, Object>> instanceMap = new HashMap<>();
    private final Map<String, Map<String, String>> permissionMap = new HashMap<>();

    private final JavaPlugin plugin;

    public CommandHandler(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void registerCommandClass(Object commandClass) {
        for (Method method : commandClass.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Command.class)) {
                Command annotation = method.getAnnotation(Command.class);
                String parent = annotation.parent();
                String sub = annotation.sub();
                String permission = annotation.permission();

                commandMap.computeIfAbsent(parent, k -> new HashMap<>()).put(sub, method);
                instanceMap.computeIfAbsent(parent, k -> new HashMap<>()).put(sub, commandClass);
                permissionMap.computeIfAbsent(parent, k -> new HashMap<>()).put(sub, permission);

                plugin.getLogger().info("Registered command: " + parent + " " + sub);
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String s, String[] args) {
        String parentCommand = command.getName().toLowerCase();
        if (args.length == 0) {
            sender.sendMessage(StringUtil.color("/" + parentCommand + " help"));
            return true;
        }

        String subCommand = args[0].toLowerCase();
        Method method = commandMap.getOrDefault(parentCommand, new HashMap<>()).get(subCommand);
        Object instance = instanceMap.getOrDefault(parentCommand, new HashMap<>()).get(subCommand);
        String permission = permissionMap.getOrDefault(parentCommand, new HashMap<>()).getOrDefault(subCommand, null);

        // 퍼미션 확인
        if (permission != null && !sender.hasPermission(permission)) {
            sender.sendMessage(NO_PERMISSION);
            return true;
        }

        // 메서드 실행
        if (method != null && instance != null) {
            try {
                method.invoke(instance, sender, args);
                return true;
            } catch (Exception e) {
                sender.sendMessage(ERROR);
                e.printStackTrace();
            }
        }

        sender.sendMessage(WRONG_COMMAND);
        return true;
    }
}

