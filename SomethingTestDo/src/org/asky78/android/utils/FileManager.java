package org.asky78.android.utils;

import java.io.*;
import java.net.*;
import java.util.*;
import org.apache.http.util.*;
import android.util.*;

public class FileManager {
	/*
웹사이트인경우
--null:HTTP/1.1 200 OK
>>X-Frame-Options:SAMEORIGIN
--Date:Wed, 23 Nov 2011 12:33:33 GMT
>>Transfer-Encoding:chunked
>>Expires:-1
>>X-XSS-Protection:1; mode=block
>>Set-Cookie:NID=53=PiuWvS8qni61prtNyePZ-sKBSevbvCQ1JH-k0qZ9nW6ncnMcL9fRonS6rNqBQW5Jq34ajjsOqj4_zXKYvF7PZ91EP5lNTzoBFfJgc_OdKOJxGebdPa_t3gg5TgSlgvUR; expires=Thu, 24-May-2012 12:33:33 GMT; path=/; domain=.google.co.kr; HttpOnly
>>Set-Cookie:PREF=ID=23b7b10c60e5ec47:FF=0:NW=1:TM=1322051613:LM=1322051613:S=KqgEhGG-RHs-uGqC; expires=Fri, 22-Nov-2013 12:33:33 GMT; path=/; domain=.google.co.kr
->Content-Type:text/html; charset=EUC-KR
--Server:gws
>>Cache-Control:private, max-age=0

파일인경우
--null:HTTP/1.1 200 OK
>>ETag:"5c6d2f-e50a-fe4ed280"
--Date:Wed, 23 Nov 2011 12:34:38 GMT
>>Content-Length:58634
>>Last-Modified:Fri, 04 Jun 2010 17:52:58 GMT
>>Keep-Alive:timeout=5, max=10000
>>Accept-Ranges:bytes
>>Connection:Keep-Alive
->Content-Type:image/gif
--Server:Apache/2.0.59 (Unix) mod_ssl/2.0.59 OpenSSL/0.9.8e-fips-rhel5 PHP/4.4.7
	 */
	public void downloadFileFromURL(String url) throws Exception {
		long startTime = System.currentTimeMillis();
//		HttpURLConnection conn = (HttpURLConnection)new URL(url).openConnection();
//		HttpURLConnection conn = (HttpURLConnection) new URL("http://www.google.com").openConnection();
		
		System.out.println(((System.currentTimeMillis() - startTime) / 1000) + "초");
	}
	
	public StringBuilder showHeaderProperty(URLConnection conn) {
		StringBuilder sb = new StringBuilder();
		Map<String, List<String>> map = conn.getHeaderFields();
		Set<String> keys = map.keySet();
		Iterator<String> it = keys.iterator();
		while(it.hasNext()){
			String key = it.next();
			List<String> list = map.get(key);
			for(String value : list){
				sb.append(key + ":" + value + "\n");
			}
		}
		return sb;
	}

	public String getHeaderProperty(URLConnection conn, String key) {
		Map<String, List<String>> map = conn.getHeaderFields();
		if(map.containsKey(key)){
			return map.get(key).get(0);
		}
		return null;
	}

	// text/html; charset=EUC-KR    ||   image/gif
	public String getContentType(URLConnection conn) {
		return conn.getContentType();
	}
	
	// 파일 사이즈
	public int getFileSize(URLConnection conn) {
		return conn.getContentLength();
	}

	public static void fileDownload() throws Exception {
		HttpURLConnection conn = (HttpURLConnection) new URL("http://cizel.co.kr/images/banner.gif").openConnection();
		InputStream is = conn.getInputStream();
		BufferedInputStream bis = new BufferedInputStream(is);
		ByteArrayBuffer baf = new ByteArrayBuffer(4096);
		int current = 0;
		while ((current = bis.read()) != -1) {
			baf.append((byte) current);
		}

		FileOutputStream fos = new FileOutputStream("D:\\android\\workspace\\AAA\\file\\ban.gif");
		fos.write(baf.toByteArray());
		// fos.flush();
		fos.close();
	}
	public void uploadFileToURL(String url) throws Exception{
		String charset = "UTF-8";
		
		String param = "value";
		File textFile = new File("/path/to/file.txt");
		File binaryFile = new File("/path/to/file.bin");
		String boundary = Long.toHexString(System.currentTimeMillis()); // Just generate some unique random value.
		String CRLF = "\r\n"; // Line separator required by multipart/form-data.

		URLConnection connection = new URL(url).openConnection();
		connection.setDoOutput(true);
		connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
		PrintWriter writer = null;
		try {
		    OutputStream output = connection.getOutputStream();
		    writer = new PrintWriter(new OutputStreamWriter(output, charset), true); // true = autoFlush, important!

		    // Send normal param.
		    writer.append("--" + boundary).append(CRLF);
		    writer.append("Content-Disposition: form-data; name=\"param\"").append(CRLF);
		    writer.append("Content-Type: text/plain; charset=" + charset).append(CRLF);
		    writer.append(CRLF);
		    writer.append(param).append(CRLF).flush();

		    // Send text file.
		    writer.append("--" + boundary).append(CRLF);
		    writer.append("Content-Disposition: form-data; name=\"textFile\"; filename=\"" + textFile.getName() + "\"").append(CRLF);
		    writer.append("Content-Type: text/plain; charset=" + charset).append(CRLF);
		    writer.append(CRLF).flush();
		    BufferedReader reader = null;
		    try {
		        reader = new BufferedReader(new InputStreamReader(new FileInputStream(textFile), charset));
		        for (String line; (line = reader.readLine()) != null;) {
		            writer.append(line).append(CRLF);
		        }
		    } finally {
		        if (reader != null) try { reader.close(); } catch (IOException logOrIgnore) {}
		    }
		    writer.flush();

		    // Send binary file.
		    writer.append("--" + boundary).append(CRLF);
		    writer.append("Content-Disposition: form-data; name=\"binaryFile\"; filename=\"" + binaryFile.getName() + "\"").append(CRLF);
		    writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(binaryFile.getName())).append(CRLF);
		    writer.append("Content-Transfer-Encoding: binary").append(CRLF);
		    writer.append(CRLF).flush();
		    InputStream input = null;
		    try {
		        input = new FileInputStream(binaryFile);
		        byte[] buffer = new byte[1024];
		        for (int length = 0; (length = input.read(buffer)) > 0;) {
		            output.write(buffer, 0, length);
		        }
		        output.flush(); // Important! Output cannot be closed. Close of writer will close output as well.
		    } finally {
		        if (input != null) try { input.close(); } catch (IOException logOrIgnore) {}
		    }
		    writer.append(CRLF).flush(); // CRLF is important! It indicates end of binary boundary.

		    // End of multipart/form-data.
		    writer.append("--" + boundary + "--").append(CRLF);
		} finally {
		    if (writer != null) writer.close();
		}
	}
	
	public void uploadFileToUrl(String file_address){
//		try {
//			URL url = new URL(file_address); 
//			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
//			connection.setDoInput(true);
//			connection.setDoOutput(true);
//			connection.setUseCaches(false);
//			connection.setRequestMethod("POST");
//		    connection.setRequestProperty("Connection", "Keep-Alive");
//		    connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=************");
//		dos = new DataOutputStream(con.getOutputStream());
//		dos = new DataOutputStream(con.getOutputStream());
//
//	    // upload files
//	    dos.writeBytes("--" + boundary + newLine);
//	    dos.writeBytes("Content-Disposition: form-data; name=\"file\";filename=\""
//	        + serverFile + "\"" + newLine + newLine);
//	bufferSize = maxBufferSize;
//	    byte[] buffer = new byte[bufferSize];
//	    while ((bytesRead = fis.read(buffer)) > -1) {
//	        dos.write(buffer, 0, bytesRead);
//	    }
	    /******************************************/
//	    dos.writeBytes(newLine);
//	    dos.writeBytes("--" + boundary + "--" + newLine);
//	dos.writeBytes(newLine);
//	    dos.writeBytes("--" + boundary + "--" + newLine);
//
//	    dos.flush();
//	    int respCode = con.getResponseCode(); 
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		
	}
	private String dirPath;
	public void getFile() {

		File dir = new File(dirPath);
		
		// 일치하는 폴더가 없으면 생성
		checkDirectory(dir);
		
		try{
		String fileUrl = "http://cizel.co.kr/images/banner.gif";
		URL url = new URL(fileUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoInput(true);
		conn.setUseCaches(false);
//		conn.setRequestMethod("POST");
//		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//		conn.setRequestProperty("Content-Length", Integer.toString(postData.length));
//		conn.setRequestProperty("Authorization", "GoogleLogin auth=" + AUTH);
			InputStream in = conn.getInputStream();
//			saveFile(dirPath + "/" + fileUrl.substring(fileUrl.lastIndexOf('/')), in);
			in.close();
		}catch (Exception e) {
			Log.i("yangdo", e.getMessage());
		}
		//파일저장
	}

	/**
	 * @param dir
	 */
	private void checkDirectory(File dir) {
		if( !dir.exists() ) {
			dir.mkdirs();
		}
	}

	/**
	 * @param saveFile
	 */
	public boolean saveFile(InputStream in, String savePath) {
		File savefile = new File(savePath);
		try{
			FileOutputStream fos = new FileOutputStream(savefile);
			byte[] buffer = new byte[2048];
			int buffSize = 0;
			while((buffSize = in.read(buffer)) > 0){
				fos.write(buffer, 0, buffSize);
			}
			fos.close();
			return true;
		} catch(IOException e){
			return false;
		}
	}
}
