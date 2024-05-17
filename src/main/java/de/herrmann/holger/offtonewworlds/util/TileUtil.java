package de.herrmann.holger.offtonewworlds.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

public class TileUtil {

    /**
     * Loads the world from the file world.txt.
     * This file contains all the tiles in the world in a list of TileTypes (as int).
     */
    public static int[][] loadWorld() {

        InputStream inputStream = TileUtil.class.getClassLoader().getResourceAsStream("assets/world/world.txt");
        if (inputStream == null) {
            throw new RuntimeException("failed to load world.txt");
        }

        List<String> lines = new LinkedList<>();
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        try {
            for (String line; (line = reader.readLine()) != null; ) {
                lines.add(line);
            }
        }
        catch (IOException e) {
            throw new RuntimeException("failed to load world from world.txt", e);
        }

        int[][] worldAsTileIntegers = null;

        int lineNumber = 0;
        for (String line : lines) {
            String[] tilesOneLine = line.split(",");
            if (worldAsTileIntegers == null) {
                worldAsTileIntegers = new int[lines.size()][tilesOneLine.length];
            }

            int rowNumber = 0;
            for (String tile : tilesOneLine) {
                worldAsTileIntegers[lineNumber][rowNumber] = Integer.parseInt(tile);

                rowNumber++;
            }

            lineNumber++;
        }

        return worldAsTileIntegers;
    }
}
