package com.github.crafttogether.kelp;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;
import java.util.function.Consumer;

public class Kelp extends ListenerAdapter {

    private static Kelp INSTANCE;
    public static Boolean connected = false;
    public static JDA jda;
    private static Consumer<ReadyEvent> readyConsumer;

    public static Kelp getInstance() {
        if (INSTANCE == null) INSTANCE = new Kelp();
        return INSTANCE;
    }

    public void connect() throws LoginException, InterruptedException {
        jda = JDABuilder.createLight(Plugin.getPlugin().getConfig().getString("token"))
                .addEventListeners(INSTANCE)
                .build()
                .awaitReady();
        connected = true;
    }

    public void disconnect() {
        if (jda == null) return;

        jda.shutdown();
    }

    public void onReady(@NotNull ReadyEvent event) {
        readyConsumer.accept(event);
    }

    public static void onReady(Consumer<ReadyEvent> consumer) {
        readyConsumer = consumer;
    }

    public static void addListeners(EventListener... eventListeners) {
        for (EventListener listener : eventListeners) {
            jda.addEventListener(listener);
        }
    }

}
