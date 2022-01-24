package com.github.crafttogether.kelp;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class Kelp extends ListenerAdapter {

    private static Kelp INSTANCE;
    private static Boolean connected = false;
    private static List<ListenerAdapter> listeners = new ArrayList<>();
    private static JDA jda;
    private static Consumer<ReadyEvent> readyConsumer = null;

    public static Kelp getInstance() {
        if (INSTANCE == null) INSTANCE = new Kelp();
        return INSTANCE;
    }

    public boolean isConnected() {
        return connected;
    }

    public JDA getClient() {
        return jda;
    }

    public void connect() throws LoginException, InterruptedException {
        jda = JDABuilder.createLight(Plugin.getPlugin().getConfig().getString("token"))
                .addEventListeners(INSTANCE, listeners)
                .build()
                .awaitReady();
        connected = true;
    }

    public void disconnect() {
        if (jda == null) return;

        jda.shutdown();
    }

    public void onReady(@NotNull ReadyEvent event) {
        if (readyConsumer != null) readyConsumer.accept(event);
    }

    public static void onReady(Consumer<ReadyEvent> consumer) {
        readyConsumer = consumer;
    }

    public static void addListeners(ListenerAdapter... eventListeners) {
        if (jda != null) {
            jda.addEventListener(Arrays.stream(eventListeners).toList());
        }
        Collections.addAll(listeners, eventListeners);
    }

}
