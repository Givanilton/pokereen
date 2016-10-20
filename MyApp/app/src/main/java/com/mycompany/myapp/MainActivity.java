package com.mycompany.myapp;

import android.app.*;
import android.os.*;
import android.content.pm.PackageManager;
import android.widget.LinearLayout;
import java.util.List;
import android.content.pm.ApplicationInfo;
import android.content.*;
import android.widget.*;
import java.io.File;
import java.io.*;
import java.util.*;


public class MainActivity extends Activity 
{
	List<String> diretorios = new ArrayList<String>();
	private AlertDialog alerta;
	private TextView    installed;
	boolean l;
	ListView listView1;
	List<String> urls = new ArrayList<>();
	String url1 = new String("https://github.com/Givanilton/pokereen/blob/master/RC2/Chinese.rc2?raw=true");
	String url2 = new String("https://github.com/Givanilton/pokereen/blob/master/RC2/items.pb?raw=true");
	String url3 = new String("https://github.com/Givanilton/pokereen/blob/master/RC2/pets.pb?raw=true");
	String url4 = new String("https://github.com/Givanilton/pokereen/blob/master/RC2/skill.pb?raw=true");
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		installed = (TextView)findViewById(R.id.installed);
		listView1 = (ListView) findViewById(R.id.listView1);
		
		urls.add(url1);
		urls.add(url2);
		urls.add(url3);
		urls.add(url4);
		
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("App");
		builder.setPositiveButton("Ok",
		new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog,
			int which){
				dialog.dismiss();
			}
		});
		
		PackageManager packageManager=getPackageManager();
        //LinearLayout linear = (LinearLayout) findViewById(R.id.linear);
        List<ApplicationInfo> list = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
		for(ApplicationInfo ap:list){
			String nome = ap.packageName;



			if (nome.equals("com.kgame.kdyg.uc")){
				installed.setText("Pokemon Remake is installed");
		               //File from = new File("/storage/emulated/0/Android/data/com.kgame.kdyg.uc/files/Xml/RC2/Chinese.rc2");
				File dir = new File("/storage/emulated/0/Android/data/com.kgame.kdyg.uc/files/Xml/RC2");
				File dyDir = new File(Environment.getExternalStorageDirectory().getPath()+"/Android/data/com.kgame.kdyg.uc/files/Xml/RC2");
				//File lista[] = dir.listFiles();
				
				/*for(int i=0; i<lista.length; i++){
					diretorios.add(lista[i].getName());
				}
				
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, diretorios);
				
				
				listView1.setAdapter(adapter);**/
				
			    
				if (dir.isDirectory()){
					//File to = new File("/storage/emulated/0/Android/data/com.kgame.kdyg.uc/files/Xml/RC2/Chinese.rc2.bkp");
					//from.renameTo(to);
					builder.setMessage("Arquivo encontrado.");
					alerta = builder.create();
					alerta.show();
				}else{
				  if(dyDir.isDirectory()){
					builder.setMessage("Arquivo encontrado2.");
					alerta = builder.create();
					alerta.show();
					}
			          else{
					builder.setMessage("Arquivo não encontrado.");
					alerta = builder.create();
					alerta.show();				  
				  }
				}
			}
		}
    }
    
	public static void gravaArquivoURL(String urls) {
		try {
			URL url = new URL(urls);
			String pathLocal = "/storage/emulated/0/Android/data/com.kgame.kdyg.uc/files/Xml/RC2/";
			String nomeArquivoLocal = url.getPath().substring(url.getPath().lastIndexOf("/"));

			File de = new File(pathLocal+nomeArquivoLocal);
			File para = new File(pathLocal+nomeArquivoLocal+".bkp");
			
			if(!para.exists()){
				de.renameTo(para);
			}
			
			InputStream is = url.openStream();

			FileOutputStream fos = new FileOutputStream(pathLocal+nomeArquivoLocal);
			
            int i = 0;
            byte[] bytesIn = new byte[1024];
            while ((i = is.read(bytesIn)) >= 0) {
                fos.write(bytesIn, 0, i);
            }

			is.close();
			fos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public String fazBackup(String localPath,List<String> arquivos){
	 File file;
	 File fileBkp;
	  for(String a : arquivos){
	   file = new File(localPath+a);
	   fileBkp = new File(localPath+a+".bkp");
	   if(file.exist()){
	    file.renameTo(fileBkp);
	     return "OK";
	   }
	   else if(fileBkp.exist()){
	    return "OK";
	   }
	   else{
	    return "Arquivos não encontrados, não foi possivel fazer o backup dos aquivos.";
	  }
	}
	public String restauraBackup(String localPath,List<String> arquivos){
	 File file;
	 File fileBkp;
	  for(String a : arquivos){
	   file = new File(localPath+a);
	   fileBkp = new File(localPath+a+".bkp");
	   if(fileBkp.exist()){
	    fileBkp.renameTo(file);
	     return "OK";
	   }
	   else{
	    return "Arquivos não encontrados, não foi possivel restaurar o backup dos aquivos.";
	  }	
	}
	
}
