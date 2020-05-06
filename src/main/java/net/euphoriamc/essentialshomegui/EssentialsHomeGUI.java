package net.euphoriamc.essentialshomegui;

import lombok.Getter;
import net.euphoriamc.essentialshomegui.commands.EditGUICommand;
import net.euphoriamc.essentialshomegui.commands.RenameTest;
import net.euphoriamc.essentialshomegui.data.DataManager;
import net.euphoriamc.essentialshomegui.utils.Holder;
import net.euphoriamc.essentialshomegui.utils.ListenerAnnotation;
import net.euphoriamc.essentialshomegui.utils.ListenerIgnore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public class EssentialsHomeGUI extends JavaPlugin {

    @Getter
    private static EssentialsHomeGUI instance;
    @Getter
    private static FileManager fileManager;
    @Getter
    private static EssentialsUtils essentialsUtils;
    @Getter
    private static DataManager dataManager;

    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getServer().getConsoleSender().sendMessage(Constants.PREFIX + ChatColor.DARK_PURPLE + "Started");

        fileManager = FileManager.getInstance().logInfo(true).setup(this);
        dataManager = new DataManager();
        essentialsUtils = new EssentialsUtils();

        initCommands();
        initListeners();
    }

    private void initListeners() {
        ConfigurationBuilder builder = new ConfigurationBuilder().addScanners(new SubTypesScanner(false), new TypeAnnotationsScanner(),
                new MethodAnnotationsScanner()).addUrls(ListenerAnnotation.class.getResource(""));
        Reflections reflections = new Reflections(builder);
        Set<Class<?>> listeners = reflections.getTypesAnnotatedWith(ListenerAnnotation.class);

        System.out.println(listeners);
        clazzLoop: for (Class<?> clazz : listeners) {
            try {
                if (clazz == Holder.class)
                    continue;
                for (AnnotatedType annotatedType : clazz.getAnnotatedInterfaces()) {
                    if (annotatedType.getAnnotation(ListenerIgnore.class) != null)
                        continue clazzLoop;
                }
                Listener instance = (Listener) clazz.getDeclaredConstructor().newInstance();
                System.out.println(instance);
                this.getServer().getPluginManager().registerEvents(instance, this);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
                System.out.println("Problem:" + clazz);
            }
        }
    }

    private void initCommands() {
        registerCommand("editgui", new EditGUICommand());
        registerCommand("renametest", new RenameTest());
    }

    private void registerCommand(String commandAlias, CommandExecutor executor) {
        this.getCommand(commandAlias).setExecutor(executor);
    }
}
