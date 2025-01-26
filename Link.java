package com.snakeladder;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Link {
    public static void openURL(String url) {
        try {
            var desktop = Desktop.getDesktop();
            var uri = new URI(url);
            desktop.browse(uri);
        } catch (IOException e) {
        } catch (URISyntaxException e) {
        }
    }
}
