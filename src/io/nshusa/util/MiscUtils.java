package io.nshusa.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.nshusa.AppData;
import io.nshusa.meta.ArchiveMeta;
import io.nshusa.meta.StoreMeta;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public final class MiscUtils {

    private MiscUtils() {

    }

    public static void saveStoreMeta() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(AppData.RESOURCE_PATH.toFile(), "stores.json")))) {
            final List<StoreMeta> meta = new ArrayList<>();

            AppData.storeNames.forEach((key, value) -> meta.add(new StoreMeta(key, value)));

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            writer.write(gson.toJson(meta));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveArchiveMeta() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(AppData.RESOURCE_PATH.resolve("archives.json").toFile()))) {

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            List<ArchiveMeta> metas = new ArrayList<>(AppData.archiveMetas.values());

            writer.write(gson.toJson(metas));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void launchURL(String url) {
        String osName = System.getProperty("os.name");
        try {
            if (osName.startsWith("Mac OS")) {
                Class<?> fileMgr = Class.forName("com.apple.eio.FileManager");
                Method openURL = fileMgr.getDeclaredMethod("openURL",
                        new Class[] {String.class});
                openURL.invoke(null, new Object[] {url});
            } else if (osName.startsWith("Windows"))
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
            else {
                String[] browsers = {"firefox", "opera", "konqueror", "epiphany", "mozilla",
                        "netscape", "safari"};
                String browser = null;
                for (int count = 0; count < browsers.length && browser == null; count++)
                    if (Runtime.getRuntime().exec(new String[] {"which", browsers[count]})
                            .waitFor() == 0)
                        browser = browsers[count];
                if (browser == null) {
                    throw new Exception("Could not find web browser");
                } else
                    Runtime.getRuntime().exec(new String[] {browser, url});
            }
        } catch (Exception ex) {
            Dialogue.showException("Failed to open URL.", ex).showAndWait();
        }
    }

}
