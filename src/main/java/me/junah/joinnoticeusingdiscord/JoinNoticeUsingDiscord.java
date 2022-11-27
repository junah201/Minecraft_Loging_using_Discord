package me.junah.joinnoticeusingdiscord;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.bukkit.Bukkit.getLogger;

public final class JoinNoticeUsingDiscord extends JavaPlugin {

    @Override
    public void onEnable() {
        Event();
        // Plugin startup logic
        getLogger().info("Event ON");

        var message = """
        {"content":"server open"}
        """;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/"))
                .POST(HttpRequest.BodyPublishers.ofString(message))
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public void Event() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new onUserJoin(), this);
    }
}
