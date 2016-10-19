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
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		installed = (TextView)findViewById(R.id.installed);
		listView1 = (ListView) findViewById(R.id.listView1);
		
		
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
				installed.setText("Pokemon Remake is installed - ");
		        File from = new File("/storage/emulated/0/Android/data/com.kgame.kdyg.uc/files/Xml/RC2/Chinese.rc2");
				File dir = new File("/storage/emulated/0/Android/data/com.kgame.kdyg.uc/files/Xml/RC2");
				File lista[] = dir.listFiles();
				
				for(int i=0; i<lista.length; i++){
					diretorios.add(lista[i].getName());
				}
				
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, diretorios);
				
				
				listView1.setAdapter(adapter);
				
				//try{
				//FileOutputStream fileOut = new FileOutputStream("/storage/emulated/0/Android/data/com.kgame.kdyg.uc/files/Xml/RC2/Chinese2.rc2");
				
				//}catch (FileNotFoundException e){}
			    
				if (from.exists()){
					File to = new File("/storage/emulated/0/Android/data/com.kgame.kdyg.uc/files/Xml/RC2/Chinese.rc2.bkp");
					from.renameTo(to);
					builder.setMessage("Arquivo encontrado.");
					alerta = builder.create();
					alerta.show();
				}else{
					builder.setMessage("Arquivo não encontrado.");
					alerta = builder.create();
					alerta.show();
				}
			}
		}
    }
}
