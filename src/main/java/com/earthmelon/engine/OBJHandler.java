package com.earthmelon.engine;

import com.earthmelon.math.Vector3f;

import java.io.*;
import java.util.ArrayList;

public class OBJHandler {

    public static SceneObject importFromOBJ(String id, String fileLocation) {
        File file = new File("src/main/resources/objects/"+fileLocation);
        if (!file.exists()) throw new IllegalArgumentException("File doesn't exist!");

        ArrayList<Vector3f> vertices = new ArrayList<>();
        ArrayList<Float> uvs = new ArrayList<>();
        ArrayList<Integer> indices = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String nextLine = reader.readLine();
            while (nextLine != null) {
                String[] split = nextLine.split(" ");
                switch (split[0]) {
                    case "v" -> vertices.add(new Vector3f(Float.parseFloat(split[1]), Float.parseFloat(split[2]), Float.parseFloat(split[3])));
                    case "f" -> {
                        if (!split[1].contains("/")) {
                            indices.add(Integer.parseInt(split[1]));
                            indices.add(Integer.parseInt(split[2]));
                            indices.add(Integer.parseInt(split[3]));
                        }
                        for (int i=1; i<split.length; i++) {
                            String[] slashSplit = split[i].split("/");
                            if (i > 3) {
                                String[] prev1 = split[i-1].split("/");
                                String[] prev2 = split[i-3].split("/");
                                indices.add(Integer.parseInt(prev1[0])-1);
                                indices.add(Integer.parseInt(slashSplit[0])-1);
                                indices.add(Integer.parseInt(prev2[0])-1);
                            } else {
                                indices.add(Integer.parseInt(slashSplit[0])-1);

                            }
                        }
                    }
                    case "vt" -> {
                        uvs.add(Float.parseFloat(split[1]));
                        uvs.add(Float.parseFloat(split[2]));

                    }
                }


                nextLine = reader.readLine();;
            }
        } catch (IOException exception) {
            throw new IllegalArgumentException("Unknown error reading file! " + exception.getMessage());
        }
        Vector3f[] outVerts = new Vector3f[vertices.size()];
        float[] outUVs = new float[uvs.size()];
        int[] outIndices = new int[indices.size()];

        for (int i=0; i< uvs.size(); i++) {
            outUVs[i] = uvs.get(i);
        }
        for (int i=0; i< indices.size(); i++) {
            outIndices[i] = indices.get(i);
        }

        return new SceneObject(id, vertices.toArray(outVerts), outUVs, outIndices);
    }

}
