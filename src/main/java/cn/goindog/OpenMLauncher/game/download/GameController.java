package cn.goindog.OpenMLauncher.game.download;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.io.File;
import java.util.Objects;

public class GameController {
    public JsonArray getInstalledVersions() {
        JsonArray installedVersions = new Gson().fromJson("[]", JsonArray.class);
        File versionDir = new File(
                System.getProperty("oml.gameDir")
                        + File.separator
                        + "versions"
                        + File.separator
        );
        for (File child : Objects.requireNonNull(versionDir.listFiles())) {
            installedVersions.add(child.getName());
        }
        return installedVersions;
    }

    public void uninstallVersion(String versionName) {
        File versionsDir = new File(
                System.getProperty("oml.gameDir")
                        + File.separator
                        + "versions"
                        + File.separator
        );
        JsonArray installedVersions = getInstalledVersions();
        for (JsonElement installedVerName : installedVersions) {
            if (installedVerName.getAsString().equals(versionName)) {
                File versionDir = new File(versionsDir.getAbsolutePath() + File.separator + versionName);
                versionDir.deleteOnExit();
            }
        }
    }
}
