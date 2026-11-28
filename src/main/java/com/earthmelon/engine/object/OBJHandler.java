package com.earthmelon.engine.object;

import com.earthmelon.math.ArrayUtils;
import com.earthmelon.math.Vector3f;
import com.earthmelon.math.Vector4f;
import com.earthmelon.render.Colour;
import com.earthmelon.render.meshes.MaterialMesh;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class OBJHandler {

    static String FILE_PATH = "src/main/resources/objects/";

    public static SceneObject importFromOBJ(String id, String fileLocation) {
        File file = new File(FILE_PATH+fileLocation);
        if (!file.exists()) throw new IllegalArgumentException("File doesn't exist!");

        HashMap<String, Material> materials = null;

        ArrayList<Vector3f> vertices = new ArrayList<>();
        ArrayList<Float> uvs = new ArrayList<>();
        HashMap<Material, Integer> indices = new HashMap<>();
        ArrayList<Vector3f> normals = new ArrayList<>();
        ArrayList<Float> indexedNormals = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            Material current_material = new Material("default");

            String nextLine = reader.readLine();
            while (nextLine != null) {
                String[] split = nextLine.split(" ");
                switch (split[0]) {
                    case "v" -> vertices.add(new Vector3f(Float.parseFloat(split[1]), Float.parseFloat(split[2]), Float.parseFloat(split[3])));
                    case "f" -> {
                        if (!split[1].contains("/")) {
                            indices.put(current_material, Integer.parseInt(split[1]));
                            indices.put(current_material, Integer.parseInt(split[2]));
                            indices.put(current_material, Integer.parseInt(split[3]));
                        }
                        for (int i=1; i<split.length; i++) {
                            String[] slashSplit = split[i].split("/");
                            if (i > 3) {
                                String[] prev1 = split[i-1].split("/");
                                String[] original = split[1].split("/");
                                indices.put(current_material, Integer.parseInt(prev1[0])-1);
                                indices.put(current_material, Integer.parseInt(slashSplit[0])-1);
                                indices.put(current_material, Integer.parseInt(original[0])-1);
                            } else {
                                indices.put(current_material, Integer.parseInt(slashSplit[0])-1);
                            }
                            if (slashSplit.length == 4) {
                                Vector3f vectorNormal = normals.get(Integer.parseInt(slashSplit[3]));
                                indexedNormals.add(vectorNormal.getX());
                                indexedNormals.add(vectorNormal.getY());
                                indexedNormals.add(vectorNormal.getZ());

                            }
                        }
                    }
                    case "vt" -> {
                        uvs.add(Float.parseFloat(split[1]));
                        uvs.add(Float.parseFloat(split[2]));

                    }
                    case "vn" -> {
                        normals.add(new Vector3f(Float.parseFloat(split[1]), Float.parseFloat(split[2]), Float.parseFloat(split[3])));
                    }
                    case "mtllib" -> {
                        materials = generateMaterials(FILE_PATH+split[1]);
                    }
                    case "usemtl" -> {
                        assert materials != null;
                        current_material = materials.get(split[1]);
                    }
                }


                nextLine = reader.readLine();
            }
        } catch (IOException exception) {
            throw new IllegalArgumentException("Unknown error reading file! " + exception.getMessage());
        }

        return new SceneObject(id, vertices.toArray(Vector3f[]::new),
                ArrayUtils.toPrimitive(uvs.toArray(Float[]::new)),
                indices,
                ArrayUtils.toPrimitive(indexedNormals.toArray(Float[]::new)));
    }

    private static HashMap<String, Material> generateMaterials(String fileName) {
        File file = new File(FILE_PATH+fileName);
        if (!file.exists()) return null;

        HashMap<String, Material> materials = new HashMap<>();
        Material current = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String nextLine = reader.readLine();
            while (nextLine != null) {
                String[] split = nextLine.split(" ");
                switch (split[0]) {
                    case "newmtl" -> {
                        current = new Material(split[1]);
                        materials.put(split[0], current);
                    }
                    case "Ka" -> {
                        assert current != null;
                        current.setAmbientColour(new Colour(new Vector4f(Float.parseFloat(split[1]),
                                Float.parseFloat(split[2]),
                                Float.parseFloat(split[3]), 1)));
                    }
                    case "Kd" -> {
                        assert current != null;
                        current.setDiffuseColour(new Colour(new Vector4f(Float.parseFloat(split[1]),
                                Float.parseFloat(split[2]),
                                Float.parseFloat(split[3]), 1)));
                    }
                    case "Ks" -> {
                        assert current != null;
                        current.setSpecularColour(new Colour(new Vector4f(Float.parseFloat(split[1]),
                                Float.parseFloat(split[2]),
                                Float.parseFloat(split[3]), 1)));
                    }
                    case "Ke" -> {
                        assert current != null;
                        current.setEmissiveColour(new Colour(new Vector4f(Float.parseFloat(split[1]),
                                Float.parseFloat(split[2]),
                                Float.parseFloat(split[3]), 1)));
                    }
                    case "Ni" -> {
                        assert current != null;
                        current.setOpticalDensity(Float.parseFloat(split[1]));
                    }
                    case "map_Kd" -> {
                        assert current != null;
                        current.setColourMap(split[1]);
                    }
                }
                nextLine = reader.readLine();
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Unknown error reading file! " + e.getMessage());
        }
        return materials;
    }

}
