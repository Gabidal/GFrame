package fi.quanfoxes;
import java.net.*;
import java.io.*;
import org.json.*;
import android.content.*;

public class fetcher
{
	
	BufferedReader input;
	URL url;
	public String responce = "";
	String line;
	public String downloadURL = "";
	
	private void getURL() throws Exception
	{
		url = new URL("https://api.github.com/repos/Gabidal/GCore/releases");
	}
	private void bufferReader() throws Exception
	{
		input = new BufferedReader(new InputStreamReader(url.openStream()));
	}
	private void getResponce() throws Exception
	{
		while ((line = input.readLine()) != null)
		{
			responce += line;
		}
		input.close();
	}
	public double getJson(String architehture)
	{
		
		try
		{
			
			getURL();
			bufferReader();
			getResponce();
			
			JSONArray json = new JSONArray(responce);
			JSONObject release = json.getJSONObject(0);
			String version = release.getString("tag_name");
			double ver = Double.parseDouble(version);
			JSONArray assets = release.getJSONArray("assets");
			for (int i = 0; i < assets.length(); i++)
			{
				JSONObject asset = assets.getJSONObject(i);
				String name = asset.getString("name");
				if (name.contains(architehture))
				{
					downloadURL = asset.getString("browser_download_url");
				}
			}
			return ver;
			
			
		} catch (Exception e)
		{
			
		}
		return 0;
	}
	public void download(String folder)
	{
		try 
		{
			BufferedInputStream in = new BufferedInputStream(new URL(downloadURL).openStream());
			FileOutputStream fileOutputStream = new FileOutputStream(folder + "/GC.obj");
			byte dataBuffer[] = new byte[1024];
			int bytesRead;
			while ((bytesRead = in.read(dataBuffer, 0, 4096)) != -1) {
				fileOutputStream.write(dataBuffer, 0, bytesRead);
			}
		} catch (IOException e) {
			// handle exception
		}
		
	}
} 
