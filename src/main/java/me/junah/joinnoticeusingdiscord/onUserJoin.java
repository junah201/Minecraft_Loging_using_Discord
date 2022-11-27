package me.junah.joinnoticeusingdiscord;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.bukkit.Bukkit.getLogger;

import java.nio.charset.Charset;


public class onUserJoin implements Listener {
    @EventHandler
    public void Join(PlayerJoinEvent event) throws IOException, InterruptedException {

        Player player = event.getPlayer();

        var message = """
        {"content":"server open"}
        """;

        String a = String.format("""
        {"content":"**%s** Joined"}
        """,player.getName());

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/"))
                .POST(HttpRequest.BodyPublishers.ofString(a))
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

        event.setJoinMessage("User Join");
    }

    @EventHandler
    public void Exit(PlayerQuitEvent event) throws IOException, InterruptedException {

        Player player = event.getPlayer();

        String a = String.format("""
        {"content":"**%s** Leaved"}
        """, player.getName());

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/"))
                .POST(HttpRequest.BodyPublishers.ofString(a))
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
}
