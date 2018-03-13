// Garrett Andrews 2017
// Created for CS559 GraphicsTown Project

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Obj2JS {
	public static void main(String[] args) throws IOException {
		ArrayList<String> vertices = new ArrayList<>();
		ArrayList<String> normals = new ArrayList<>();
		ArrayList<String> textCoords = new ArrayList<>();
		String currDir = System.getProperty("user.dir");
		FileReader reader = new FileReader(currDir + "/src/tieBomber.txt");
		BufferedReader bReader = new BufferedReader(reader);
		String line;
		while ((line = bReader.readLine()) != null) {
			if (!line.contains("#")) {
				String[] data = line.split(" ");
				if (data[0].equals("v")) {
					// this is a vertex component
					// there are 2 ' ' after the 'v' so we need to offset the reference by 1
					vertices.add(data[1]); // X
					vertices.add(data[2]); // Y
					vertices.add(data[3]); // Z
				}
				else if (data[0].equals("vn")) {
					// this is a normal component
					normals.add(data[1]); // X
					normals.add(data[2]); // Y
					normals.add(data[3]); // Z
				}
				else if (data[0].equals("vt")) {
					// this is a texture coordinate component
					textCoords.add(data[1]); // X
					textCoords.add(data[2]); // Y
				}
			}
		}
		System.out.println("Done PART 1");
		String output = "";
		int count = 0;
		output += "var RENAMEGLOBAL_DATA = {" + '\n';
		output += "object : {" + '\n';
		output += "vertex : { numComponents : 3," + '\n';
		output += "data : [";
		for (int k = 0; k < vertices.size(); k++) {
			if (k==vertices.size()-1) {
				output += vertices.get(k) + "]}," + '\n';
			}
			else {
				count ++;
				output += vertices.get(k) + ", ";
				if (count == 3) {
					// after adding 3 components
					output += '\n';
					count = 0;
				}
			}
		}
		System.out.println("Done PART 2");
		count = 0;
		output += "normal : { numComponents : 3," + '\n';
		output += "data : [";
		for (int j = 0; j < normals.size(); j++) {
			if (j==normals.size()-1) {
				output += normals.get(j) + "]}," + '\n';
			}
			else {
				count ++;
				output += normals.get(j) + ", ";
				if (count == 3) {
					// after adding 3 components
					output += '\n';
					count = 0;
				}
			}
		}
		System.out.println("Done PART 3");
		count = 0;
		output += "texcoord : { numComponents : 2," + '\n';
		output += "data : [";
		for (int l = 0; l < textCoords.size(); l++) {
			if (l==textCoords.size()-1) {
				output += textCoords.get(l) + "]}," + '\n';
			}
			else {
				count ++;
				output += textCoords.get(l) + ", ";
				if (count == 2) {
					// after adding 2 components
					output += '\n';
					count = 0;
				}
			}
		}
		System.out.println("Done PART 4");
		count = 0;
		output += "color : { numComponents : 3," + '\n';
		output += "data : [";
		for (int m = 0; m < normals.size(); m++) {
			if (m==vertices.size()-1) {
				output += "1.0" + "]}," + '\n';
			}
			else {
				count ++;
				output += "1.0" + ", ";
				if (count == 3) {
					// after adding 3 components
					output += '\n';
					count = 0;
				}
			}
		}
		output += "}" + '\n' + "};";
		File result = new File(currDir + "/src/tieFighter_data.js");
		BufferedWriter out = new BufferedWriter(new FileWriter(result));
		out.write(output);
		bReader.close();
		out.close();
		System.out.println("DONE, " + result.getAbsolutePath());
		System.out.println("X1" + vertices.get(0));
		System.out.println("X2" + vertices.get(3));
		System.out.println("X3" + vertices.get(6));
	}
}
