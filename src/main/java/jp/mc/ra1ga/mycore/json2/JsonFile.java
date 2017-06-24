package jp.mc.ra1ga.mycore.json2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.bukkit.plugin.java.JavaPlugin;

import com.google.gson.Gson;

public abstract class JsonFile {

	private JsonFile() {
	}

	public static File loadFile(JavaPlugin plugin, String fileName) {
		File file = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + fileName);

		plugin.getDataFolder().mkdirs();
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return file;
	}

	public static String loadJsonToString(JavaPlugin plugin, String fileName) {
		File file = JsonFile.loadFile(plugin, fileName);
		String json = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));

			String line;
			while((line = br.readLine()) != null) {
				json += line;
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return json;
	}

	public static <T> T loadJsonToObject(JavaPlugin plugin, String fileName, Class<T> clazz) {
		File file = JsonFile.loadFile(plugin, fileName);
		T obj = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));

			String json = "";
			String line;
			while((line = br.readLine()) != null) {
				json += line;
			}

			Gson gson = new Gson();
			obj = gson.fromJson(json, clazz);

			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return obj;
	}

	public static void writeJson(JavaPlugin plugin, String fileName, Object obj) {
		File file = loadFile(plugin, fileName);
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			PrintWriter pw = new PrintWriter(bw);

			Gson gson = new Gson();
			pw.print(gson.toJson(obj));

			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
