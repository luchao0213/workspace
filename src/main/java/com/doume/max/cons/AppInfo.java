package com.doume.max.cons;
import com.sina.sae.util.SaeUserInfo;
public class AppInfo {
	public String appName;
	public String username;
	public String password;
	public String saeTmpPath;
	public String url;
	public String driver;
	public AppInfo(){
		username = SaeUserInfo.getAccessKey();
		password = SaeUserInfo.getSecretKey();
		appName = SaeUserInfo.getAppName();
		saeTmpPath = SaeUserInfo.getSaeTmpPath();
		url = "jdbc:mysql://r.rdc.sae.sina.com.cn:3307/app_"+SaeUserInfo.getAppName();
		driver = "com.mysql.jdbc.Driver";
	}
}
